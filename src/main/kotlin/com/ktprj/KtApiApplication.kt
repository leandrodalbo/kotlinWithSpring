package com.ktprj

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KtApiApplication

fun main(args: Array<String>) {
    runApplication<KtApiApplication>(*args)
}
