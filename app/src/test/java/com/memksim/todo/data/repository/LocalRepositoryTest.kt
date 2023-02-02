package com.memksim.todo.data.repository

import com.memksim.todo.fakeCompletedDtoList
import com.memksim.todo.fakeDto
import com.memksim.todo.fakeUpcomingDtoList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class LocalRepositoryTest {

    lateinit var repository: LocalRepository

    @BeforeEach
    private fun init(){
        repository = mockk()
    }

    @Test
    fun `check returning correct upcoming dto list`() = runTest {
        coEvery { repository.getUpcomingTasks() } returns fakeUpcomingDtoList

        val expected = fakeUpcomingDtoList
        val actual = repository.getUpcomingTasks()

        coVerify { repository.getUpcomingTasks() }

        Assertions.assertArrayEquals(expected.toTypedArray(), actual.toTypedArray())
    }

    @Test
    fun `check returning correct completed dto list`() = runTest {
        coEvery { repository.getCompletedTasks() } returns fakeCompletedDtoList

        val expected = fakeCompletedDtoList
        val actual = repository.getCompletedTasks()

        coVerify { repository.getCompletedTasks() }

        Assertions.assertArrayEquals(expected.toTypedArray(), actual.toTypedArray())
    }

    @Test
    fun `check returning correct dto with correct args`() = runTest {
        val id = 1
        coEvery { repository.getTask(id = id) } returns fakeDto

        val expected = fakeDto
        val actual = repository.getTask(id = id)

        coVerify { repository.getTask(id = id) }

        Assertions.assertEquals(expected, actual)
    }

}