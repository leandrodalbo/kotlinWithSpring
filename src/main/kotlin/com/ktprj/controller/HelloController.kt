package com.ktprj.controller

import com.ktprj.service.HelloService
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam


@RestController
@RequestMapping("hello")
class HelloController(val service: HelloService) {

    @GetMapping("/{name}")
    fun sayHello(@PathVariable name: String): String {
        return service.sayHello(name)
    }
}