package com.evalify.evalifybackend.quiz.service


import com.evalify.evalifybackend.course.repository.CourseRepository
import com.evalify.evalifybackend.quiz.domain.Quiz
import com.evalify.evalifybackend.quiz.domain.QuizStudent
import com.evalify.evalifybackend.quiz.repository.QuizRepository
import com.evalify.evalifybackend.quiz.repository.QuizStudentRepository
import com.evalify.evalifybackend.usewr.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

@Service
class QuizStudentService(
    val quizRepository: QuizRepository,
    val userRepository: UserRepository,
    val quizStudentRepository: QuizStudentRepository,
    val courseRepository: CourseRepository,
) {

    fun getAllQuiz(studentId: UUID): List<Quiz> {
        val studentCourses = courseRepository.findCoursesByStudentId(studentId)
        val quizzes = studentCourses.stream().map{
            course -> course.quiz
        }.toList()

        return quizzes.flatten()

    }

    fun getQuizQuestions(quizId: UUID,studentId: UUID,ipAddress:String){
        val quiz = quizRepository.findById(quizId).orElseThrow()
        val user = userRepository.findById(studentId)

        //First time entering into a quiz
        val quizStudent = quizStudentRepository.findByQuizIdAndStudentId(quizId,studentId) ?: quizStudentRepository.save(
            QuizStudent(
                quiz = quiz,
                student = user.get(),
                isSubmitted = false,
                startTime = Instant.now(),
                duration = quiz.duration,
                endTime = null,
                ipAddress = mutableListOf(ipAddress)
            )
        )

        //Adding new ip address
        if(!quizStudent.ipAddress.contains(ipAddress)){
            quizStudent.ipAddress.add(ipAddress)
            quizStudentRepository.save(quizStudent);
        }
    }
}