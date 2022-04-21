package com.ktprj.service

import com.ktprj.adapters.CourseAdapters
import com.ktprj.dto.CourseDto
import com.ktprj.entities.Course
import com.ktprj.repository.CourseRepository
import org.springframework.stereotype.Service
import java.io.InvalidObjectException

@Service
class CourseService(val courseRepository: CourseRepository, val courseAdapters: CourseAdapters) {

    fun newCourse(courseDto: CourseDto): CourseDto {

        val course = courseAdapters.dtoToEntity(courseDto)

        return courseAdapters.entityToDto(courseRepository.save(course))
    }

    fun getAll(): List<CourseDto> {
        return courseRepository
            .findAll()
            .asSequence()
            .map { courseAdapters.entityToDto(it) }
            .toList()
    }

    fun updateCourse(id: Int, courseDto: CourseDto): CourseDto? {
        val updatingCourse: Course? = courseRepository.findById(id).orElseGet({ null })

        return when (updatingCourse) {
            null -> null
            else -> {
                return courseAdapters.entityToDto(
                    courseRepository.save(
                        updatingCourse.copy(
                            name = courseDto.name,
                            category = courseDto.category
                        )
                    )
                )
            }
        }

    }

    fun deleteCourse(id: Int): String? {
        val updatingCourse: Course? = courseRepository.findById(id).orElseGet({ null })

        return when (updatingCourse) {
            null -> null
            else -> {
                courseRepository.deleteById(id)
                return "DELETED ${id}"

            }
        }

    }
}