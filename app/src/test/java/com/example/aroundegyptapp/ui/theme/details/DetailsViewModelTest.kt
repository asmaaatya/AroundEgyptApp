package com.example.aroundegyptapp.ui.theme.details

import com.example.aroundegyptapp.data.model.details.DetailsData
import com.example.aroundegyptapp.data.repo.DetailsRepo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    private lateinit var viewModel: DetailsViewModel
    private val mockRepository: DetailsRepo = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DetailsViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchDetails should update details StateFlow`() = runTest {
        val experienceId = "7f209d18-36a1-44d5-a0ed-b7eddfad48d6"
        val mockDetails = mockk<DetailsData>()
        coEvery { mockRepository.getSingleExperience(experienceId) } returns mockDetails
        viewModel.fetchDetails(experienceId)
        testDispatcher.scheduler.advanceUntilIdle()
        val details = viewModel.details.first()
        assertEquals(mockDetails, details)
        coVerify { mockRepository.getSingleExperience(experienceId) }
    }


    @Test
    fun `likeExperience should update likesMap StateFlow`() = runTest {
        val postId = "7f209d18-36a1-44d5-a0ed-b7eddfad48d6"
        val newLikes = 10
        coEvery { mockRepository.likeExperience(postId) } returns newLikes
        viewModel.likeExperience(postId)
        testDispatcher.scheduler.advanceUntilIdle()
        val likesMap = viewModel.likesMap.first()
        assertEquals(newLikes, likesMap[postId])
        coVerify { mockRepository.likeExperience(postId) }
    }
}