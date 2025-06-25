package com.evalify.evalifybackend.quiz.service

import com.github.sonus21.rqueue.annotation.RqueueListener
import com.github.sonus21.rqueue.core.RqueueMessageEnqueuer
import org.springframework.stereotype.Service

@Service
class RedisService(
    private val rqueueMessageEnqueuer: RqueueMessageEnqueuer
) {
     val  queueName = "quiz"

    fun pushToQueue(data: String) {
        rqueueMessageEnqueuer.enqueue(queueName, data);

        println("Pushed: $data")
    }

    @RqueueListener(value = ["quiz"], concurrency = "3")
    fun handleQuiz(message:String){
        System.out.println("Recieved message:" + message)
    }

}