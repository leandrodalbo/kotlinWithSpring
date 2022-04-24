package com.ktprj.entities

import javax.persistence.*

@Entity
@Table(name = "Courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CourseID")
    val id: Int?,
    val name: String,
    val category: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructorId", nullable = false)
    val instructor: Instructor

) {
    override fun toString(): String {
        return "id=${id}, name=${name}, instructor=${instructor.id}"
    }
}