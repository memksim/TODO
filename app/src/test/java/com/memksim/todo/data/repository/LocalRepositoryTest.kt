package com.memksim.todo.data.repository

import com.memksim.todo.fakeCompletedDtoList
import com.memksim.todo.fakeDto
import com.memksim.todo.fakeUpcomingDtoList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LocalRepositoryTest {

    lateinit var repository: LocalRepository

    @BeforeEach
    private fun init(){
        repository = mockk()
    }

    @Test
    fun `check returning correct upcoming dto list`() = runTest {
        coEvery { repository.getUpcomingReminders() } returns fakeUpcomingDtoList

        val expected = fakeUpcomingDtoList
        val actual = repository.getUpcomingReminders()

        coVerify { repository.getUpcomingReminders() }

        Assertions.assertArrayEquals(expected.toTypedArray(), actual.toTypedArray())
    }

    @Test
    fun `check returning correct completed dto list`() = runTest {
        coEvery { repository.getCompletedReminders() } returns fakeCompletedDtoList

        val expected = fakeCompletedDtoList
        val actual = repository.getCompletedReminders()

        coVerify { repository.getCompletedReminders() }

        Assertions.assertArrayEquals(expected.toTypedArray(), actual.toTypedArray())
    }

    @Test
    fun `check returning correct dto with correct args`() = runTest {
        val id = 1
        coEvery { repository.getReminder(id = id) } returns fakeDto

        val expected = fakeDto
        val actual = repository.getReminder(id = id)

        coVerify { repository.getReminder(id = id) }

        Assertions.assertEquals(expected, actual)
    }

}