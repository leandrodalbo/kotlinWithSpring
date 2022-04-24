package com.ktprj.dto

import javax.validation.constraints.NotBlank

data class InstructorDto(
    val id: Int?,
    @get:NotBlank(message = "Instructor Name is required") val name: String,
)