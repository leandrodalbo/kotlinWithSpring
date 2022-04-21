package com.ktprj.adapters

import com.ktprj.dto.CourseDto
import com.ktprj.entities.Course
import org.springframework.stereotype.Component

@Component
class CourseAdapters {

    fun entityToDto(course: Course): CourseDto {
        return course.let {
            CourseDto(id = it.id, name = it.name, category = it.category)
        }
    }

    fun dtoToEntity(course: CourseDto): Course {
        return course.let { Course(null, name = it.name, category = it.category) }
    }

}