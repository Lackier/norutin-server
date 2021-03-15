package com.app.norutin.security

import com.app.norutin.enums.UserRole
import com.google.api.client.util.Strings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import lombok.extern.slf4j.Slf4j
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@Component
class FirebaseAuthTokenFilter : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        logger.debug("FirebaseAuthenticationTokenFilter: authenticating")
        val authToken = request.getHeader(TOKEN_HEADER)
        if (Strings.isNullOrEmpty(authToken)) {
            filterChain.doFilter(request, response)
            return
        }
        try {
            val authentication = getAndValidateAuthentication(authToken)
            SecurityContextHolder.getContext().authentication = authentication
            logger.debug("Successfully authenticated.")
        } catch (ex: Exception) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            logger.debug("Authentication failed", ex)
        }
        filterChain.doFilter(request, response)
    }

    @Throws(Exception::class)
    private fun getAndValidateAuthentication(authToken: String): Authentication {
        val firebaseToken = authenticateFirebaseToken(authToken)
        return UsernamePasswordAuthenticationToken(firebaseToken, authToken, setOf(UserRole.USER))
    }

    @Throws(Exception::class)
    private fun authenticateFirebaseToken(authTokenFirebase: String): FirebaseToken {
        var authToken = authTokenFirebase
        authToken = authToken.substring(7)
        val app = FirebaseAuth.getInstance().verifyIdTokenAsync(authToken)
        return app.get()
    }

    companion object {
        private const val TOKEN_HEADER = "Authorization"
    }
}