package com.github.leleact.kotlin.tutorial

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ScopeFunctionTests {

    val log: Logger = LoggerFactory.getLogger(ScopeFunctionTests::class.java)

    @Test
    fun `let`() {
        val numbers = mutableListOf("one", "two", "three", "four", "five")
        numbers.map { it.length }.filter { it > 3 }.let {
            log.info("it: {}", it)
        }
    }

    @Test
    fun `apply`() {
        val numbers = mutableListOf("one", "two", "three", "four", "five")
        numbers.map { it.length }.filter { it > 3 }.apply {
            log.info("this: {}", this)
        }
    }
}