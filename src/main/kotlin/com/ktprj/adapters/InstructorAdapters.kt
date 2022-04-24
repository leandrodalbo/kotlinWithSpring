package com.ktprj.adapters

import com.ktprj.dto.InstructorDto
import com.ktprj.entities.Instructor
import org.springframework.stereotype.Component

@Component
class InstructorAdapters {

    fun entityToDto(instructor: Instructor): InstructorDto {
        return instructor.let {
            InstructorDto(id = it.id, name = it.name)
        }
    }

    fun dtoToEntity(instructor: InstructorDto): Instructor {
        return instructor.let { Instructor(null, name = it.name) }
    }

}