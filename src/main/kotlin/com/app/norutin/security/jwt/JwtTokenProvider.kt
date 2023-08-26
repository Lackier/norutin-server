package com.app.norutin.security.jwt

import com.app.norutin.model.Role
import com.app.norutin.security.JwtUserDetailServiceImpl
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    @Lazy val userDetailServiceImpl: JwtUserDetailServiceImpl
) {

    private var secret = "secret"
    private val validityInMilliseconds = 3600000
    private var tokens: MutableList<String> = mutableListOf()

    @PostConstruct
    protected fun init() {
        secret = Base64.getEncoder().encodeToString(secret.toByteArray())
    }

    fun createToken(username: String, roles: List<Role>): String {
        val claims = Jwts.claims().setSubject(username)
        claims["roles"] = getRoleNames(roles)

        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)
        val token = Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date())
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()

        tokens.add(token)

        return token
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailServiceImpl.loadUserByUsername(getUsernameFromToken(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsernameFromToken(token: String): String {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body.subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }

    fun validateToken(token: String): Boolean {
        if (!tokens.contains(token)) {
            throw JwtAuthenticationException("JWT token is expired or invalid")
        }

        return try {
            val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            throw JwtAuthenticationException("JWT token is expired or invalid")
        }
    }

    fun getRoleNames(userRoles: List<Role>): List<String> {
        val result: MutableList<String> = ArrayList()

        userRoles.forEach { role -> result.add(role.name) }

        return result
    }

    fun logout(request: HttpServletRequest) {
        val token = this.resolveToken(request)
        tokens.remove(token)
    }
}