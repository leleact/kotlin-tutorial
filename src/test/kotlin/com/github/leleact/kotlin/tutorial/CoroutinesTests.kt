package com.github.leleact.kotlin.tutorial

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CoroutinesTests {

    private val log: Logger = LoggerFactory.getLogger(CoroutinesTests::class.java)

    @Test
    fun `coroutines test`() {
        GlobalScope.launch {
            // launch a new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
        println("Hello,") // main thread continues while coroutine is delayed
        Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
    }

    @Test
    fun `run blocking`() = runBlocking<Unit> {
        GlobalScope.launch {
            // launch a new coroutine in background and continue
            delay(1000L)
            log.info("World!")
        }
        log.info("Hello,") // main coroutine continues here immediately
        delay(2000L)      // delaying for 2 seconds to keep JVM alive
    }

    @Test
    fun `Structured concurrency`() = runBlocking {
        // this: CoroutineScope
        launch {
            // launch a new coroutine in the scope of runBlocking
            delay(1000L)
            log.info("World!")
        }
        log.info("Hello,")
    }

    @Test
    fun `Scope builder`() = runBlocking { // this: CoroutineScope
        launch {
            delay(200L)
            log.info("Task from runBlocking")
        }

        coroutineScope { // Creates a coroutine scope
            launch {
                delay(500L)
                log.info("Task from nested launch")
            }

            delay(100L)
            log.info("Task from coroutine scope") // This line will be printed before the nested launch
        }

        log.info("Coroutine scope is over") // This line is not printed until the nested launch completes
    }

    @Test
    fun `Coroutines ARE light-weight`() = runBlocking {
        repeat(100_000) { // launch a lot of coroutines
            launch {
                delay(1000L)
                log.info(".")
            }
        }
    }
}
