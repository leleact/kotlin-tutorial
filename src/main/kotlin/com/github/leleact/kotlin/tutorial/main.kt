package com.github.leleact.kotlin.tutorial

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Application {
    companion object {
        val log: Logger = LoggerFactory.getLogger(Application::class.java)
    }
}

fun main(args: Array<String>) {
    Application.log.info("args: {}", args)
}
