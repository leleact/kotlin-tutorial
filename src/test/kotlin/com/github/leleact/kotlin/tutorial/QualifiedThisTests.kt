package com.github.leleact.kotlin.tutorial

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * <pre>
 * class A { // implicit label @A
 *     inner class B { // implicit label @B
 *         fun Int.foo() { // implicit label @foo
 *             val a = this@A // A's this
 *             val b = this@B // B's this
 *
 *             val c = this // foo()'s receiver, an Int
 *             val c1 = this@foo // foo()'s receiver, an Int
 *
 *             val funLit = lambda@ fun String.() {
 *                 val d = this // funLit's receiver
 *             }
 *
 *
 *             val funLit2 = { s: String ->
 *                 // foo()'s receiver, since enclosing lambda expression
 *                 // doesn't have any receiver
 *                 val d1 = this
 *             }
 *         }
 *     }
 * }
 * </pre>
 */
class QualifiedThisTests {

    private val log: Logger = LoggerFactory.getLogger(QualifiedThisTests::class.java)

    fun Int.foo() {
        val c = this // foo()'s receiver, an Int
        log.info("c: {}", c)

        val c1 = this@foo // foo()'s receiver, an Int
        log.info("c1: {}", c1)

        val funLit = lambda@ fun String.() {
            val d = this // funLit's receiver
            log.info("d: {}", d)
        }
        val funLit2 = { s: String ->
            // foo()'s receiver, since enclosing lambda expression
            // doesn't have any receiver
            val d1 = this
            log.info("s: {}, d1: {}", s, d1)
        }

        val abc = "abc"
        abc.funLit()

        funLit2(abc)
    }

    @Test
    @DisplayName("qualified this receiver test")
    fun `receiver test`() {
        val a = 100
        a.foo()
    }
}

