package com.ktprj.repository

import com.ktprj.entities.Course
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : CrudRepository<Course, Int> {
    fun findByNameContaining(value: String): List<Course>

    @Query(value = "SELECT * FROM COURSES WHERE category LIKE %?1%", nativeQuery = true)
    fun findByCategoryContaining(value: String): List<Course>
}