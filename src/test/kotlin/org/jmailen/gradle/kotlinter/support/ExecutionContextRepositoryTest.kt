package org.jmailen.gradle.kotlinter.support

import com.nhaarman.mockitokotlin2.mock
import org.jmailen.gradle.kotlinter.tasks.lint.LintExecutionContext
import org.junit.Assert.assertEquals
import org.junit.Test

class ExecutionContextRepositoryTest {

    @Test
    fun getRegisteredContextWorks() {
        val repository = ExecutionContextRepository.lintInstance
        val executionContext = LintExecutionContext(emptyList(), mock())
        val id = repository.register(executionContext)

        val result = repository.get(id)

        assertEquals(executionContext, result)
    }

    @Test(expected = NoSuchElementException::class)
    fun getUnregisteredContextFails() {
        val repository = ExecutionContextRepository.lintInstance
        val executionContext = LintExecutionContext(emptyList(), mock())
        val id = repository.register(executionContext)
        repository.unregister(id)

        repository.get(id)
    }
}