package com.ktprj.controller

import com.ktprj.dto.CourseDto
import com.ktprj.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.io.InvalidObjectException

@RestController
@RequestMapping("/courses")
class CourseController(val courseService: CourseService) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCourse(@RequestBody courseDto: CourseDto): CourseDto {
        if (courseDto.id != null) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Id is not needed")
        }
        return courseService.newCourse(courseDto)
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllCourses(): List<CourseDto> {
        return courseService.getAll()
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCourse(@PathVariable id: Int, @RequestBody courseDto: CourseDto): CourseDto {

        return courseService.updateCourse(id, courseDto)
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Course Not Found")

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCourse(@PathVariable id: Int): String {
        return courseService.deleteCourse(id)
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Course Not Found")

    }
}