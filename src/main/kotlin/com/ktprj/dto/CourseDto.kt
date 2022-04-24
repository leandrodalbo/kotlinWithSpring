package com.ktprj.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CourseDto(
    val id: Int?,
    @get:NotBlank(message = "Course Name is required") val name: String,
    @get:NotBlank(message = "Course Category is required") val category: String,
    @get:NotNull(message = "InstructorId is required") val instructorId: Int
)