package com.ktprj.controller

import com.ktprj.dto.CourseDto
import com.ktprj.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/courses")
@Validated
class CourseController(val courseService: CourseService) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCourse(@RequestBody @Valid courseDto: CourseDto): CourseDto {
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
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCourse(@PathVariable id: Int): String {
        return courseService.deleteCourse(id)
    }
}