package com.evalify.evalifybackend.course.repository

import com.evalify.evalifybackend.course.domain.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.UUID


@RepositoryRestResource(path = "course")
interface CourseRepository : JpaRepository<Course,UUID>{

    @Query("SELECT c FROM Course c JOIN c.student s WHERE s.id = :studentId")
    fun findCoursesByStudentId(studentId: UUID): List<Course>
}