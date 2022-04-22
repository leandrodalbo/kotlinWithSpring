package com.ktprj.service

import com.ktprj.adapters.CourseAdapters
import com.ktprj.dto.CourseDto
import com.ktprj.entities.Course
import com.ktprj.repository.CourseRepository
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import javax.validation.Valid

@Service
@Validated
class CourseService(val courseRepository: CourseRepository, val courseAdapters: CourseAdapters) {

    fun newCourse(@Valid courseDto: CourseDto): CourseDto {
        return when (courseDto.id != null) {
            true -> {
                throw  Exception("Id is autogenerated, it is not a required field")
            }
            else -> {
                val course = courseAdapters.dtoToEntity(courseDto)

                return courseAdapters.entityToDto(courseRepository.save(course))
            }
        }

    }

    fun getAll(): List<CourseDto> {
        return courseRepository
            .findAll()
            .asSequence()
            .map { courseAdapters.entityToDto(it) }
            .toList()
    }

    fun updateCourse(id: Int, courseDto: CourseDto): CourseDto {
        val updatingCourse: Course? = courseRepository.findById(id).orElseGet({ null })

        return when (updatingCourse) {
            null -> throw Exception("Course Not Found")
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

    fun deleteCourse(id: Int): String {
        val updatingCourse: Course? = courseRepository.findById(id).orElseGet({ null })

        return when (updatingCourse) {
            null -> throw Exception("Course Not Found")
            else -> {
                courseRepository.deleteById(id)
                return "DELETED ${id}"

            }
        }

    }
}