package com.example.aroundegyptapp.data.repo

import com.example.aroundegyptapp.data.model.details.DetailsData
import com.example.aroundegyptapp.data.model.details.DetailsResp
import com.example.aroundegyptapp.data.model.like.LikeExpResp
import com.example.aroundegyptapp.data.network.ApiService
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsRepoTest {
    private val mockApiService = mockk<ApiService>()
    private var mockRepository: DetailsRepo = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockRepository = DetailsRepo(mockApiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun getSingleExperience() = runTest {
        val mockApiService: ApiService = mockk()
        val mockExperience = mockk<DetailsData>()
        val mockResponse = mockk<DetailsResp>()
        val id="7f209d18-36a1-44d5-a0ed-b7eddfad48d6"
        coEvery { mockApiService.getSingleExperience(id) } returns mockResponse
        every { mockResponse.data } returns mockExperience
        val repo = DetailsRepo(mockApiService)
        val result = repo.getSingleExperience(id)
        assertEquals(mockExperience, result)
    }


    @Test
    fun likeExperience() = runTest {
        val mockApiService: ApiService = mockk()
        val mockResponse = mockk<LikeExpResp>()
        val id="7f209d18-36a1-44d5-a0ed-b7eddfad48d6"
        every { mockResponse.data } returns 10
        coEvery { mockApiService.likeExperience(id) } returns mockResponse
        val repo = DetailsRepo(mockApiService)
        val result = repo.likeExperience(id)
        assertEquals(10, result)
    }

}