package com.evalify.evalifybackend.quiz.controller.redis_controller

import com.evalify.evalifybackend.quiz.service.RedisService
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RedisController (
    private val redisService: RedisService
){

    @PostMapping("/send")
    fun sendToQueue(@RequestBody data: String): String {
        redisService.pushToQueue(data)
        return "Sent to queue"
    }
}