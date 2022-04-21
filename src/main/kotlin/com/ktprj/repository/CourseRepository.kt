package com.ktprj.repository

import com.ktprj.entities.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository : CrudRepository<Course, Int>