package com.ktprj.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class HelloService {

    @Value("\${message}")
    lateinit var message: String

    fun sayHello(name: String): String = "${message} mr. ${name}"

}