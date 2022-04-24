package com.ktprj.adapters

import com.ktprj.dto.CourseDto
import com.ktprj.entities.Course
import com.ktprj.service.InstructorService
import org.springframework.stereotype.Component

@Component
class CourseAdapters(val instructorService: InstructorService) {

    fun entityToDto(course: Course): CourseDto {
        return course.let {
            CourseDto(id = it.id, name = it.name, category = it.category, instructorId = it.instructor.id!!)
        }
    }

    fun dtoToEntity(course: CourseDto): Course {
        return course.let {
            val instructor = instructorService.findInstructorById(course.instructorId)

            return when (instructor.isPresent) {
                true -> Course(null, name = it.name, category = it.category, instructor.get())
                else -> throw Exception("A Valid Instructor is Required to create a course")
            }

        }
    }

}