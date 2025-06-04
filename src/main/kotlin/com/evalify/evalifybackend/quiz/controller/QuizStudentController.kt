package com.evalify.evalifybackend.quiz.controller

import com.evalify.evalifybackend.quiz.domain.DTO.QuizStudentDTO
import com.evalify.evalifybackend.quiz.service.QuizStudentService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("student{studentId}/quiz/")
class QuizStudentController(
    val quizStudentService: QuizStudentService,
) {

    @GetMapping("{id}")
    fun getQuiz(@PathVariable studentId: UUID,@PathVariable id: UUID, request: HttpServletRequest){
        quizStudentService.getQuizQuestions(studentId = studentId, quizId = id, ipAddress = request.remoteAddr)
    }

    @GetMapping
    fun getAllQuiz(@PathVariable studentId: UUID):List<QuizStudentDTO> {
       return quizStudentService.getAllQuiz(studentId).stream().map {
            quiz -> QuizStudentDTO(
            id = quiz.id,
            name = quiz.name,
            description = quiz.description,
            instructions = quiz.instructions,
            endTime = quiz.endTime,
            startTime = quiz.startTime,
            fullScreen = quiz.fullScreen,
            shuffleOptions = quiz.shuffleOptions,
            linearQuiz = quiz.linearQuiz,
            calculator = quiz.calculator,
            autoSubmit = quiz.autoSubmit,
            shuffleQuestions = quiz.shuffleQuestions
        )
        }.toList()
    }
}