package com.app.norutin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan
class NorutinApplication

fun main(args: Array<String>) {
    runApplication<NorutinApplication>(*args)
}
