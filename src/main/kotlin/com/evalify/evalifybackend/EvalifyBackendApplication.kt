package com.evalify.evalifybackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling

class EvalifyBackendApplication

fun main(args: Array<String>) {

	// Loading Environment Variables
	com.evalify.evalifybackend.core.config.DotenvLoader.load()

	runApplication<EvalifyBackendApplication>(*args)
}
