package com.ktprj.repository

import com.ktprj.entities.Instructor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InstructorRepository : CrudRepository<Instructor, Int>