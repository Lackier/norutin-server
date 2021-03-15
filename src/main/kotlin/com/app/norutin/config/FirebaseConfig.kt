package com.app.norutin.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.IOException
import javax.annotation.PostConstruct

@Configuration
class FirebaseConfig {
    @Bean
    fun firebaseDatabase(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference
    }

    @Value("\${firebase.database.url}")
    private val databaseUrl: String? = null

    @Value("\${firebase.config.path}")
    private val configPath: String? = null

    @PostConstruct
    @Throws(IOException::class)
    fun init() {
        val inputStream = FirebaseConfig::class.java.classLoader.getResourceAsStream(configPath)!!
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(inputStream))
            .setDatabaseUrl(databaseUrl).build()
        FirebaseApp.initializeApp(options)
    }
}
