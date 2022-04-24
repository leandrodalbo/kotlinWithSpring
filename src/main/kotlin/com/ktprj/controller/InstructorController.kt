package com.ktprj.controller

import com.ktprj.dto.InstructorDto
import com.ktprj.service.InstructorService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/instructors")
@Validated
class InstructorController(val instructorService: InstructorService) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCourse(@RequestBody @Valid instructorDto: InstructorDto): InstructorDto {
        return instructorService.newCourse(instructorDto)
    }

}