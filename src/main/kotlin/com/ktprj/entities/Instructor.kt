package com.ktprj.entities

import javax.persistence.*

@Entity
@Table(name = "Instructor")
data class Instructor(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "instructorId")
    val id: Int?,
    val name: String,


    @OneToMany(mappedBy = "instructor", cascade = [CascadeType.ALL])
    var courses: List<Course> = mutableListOf()
)