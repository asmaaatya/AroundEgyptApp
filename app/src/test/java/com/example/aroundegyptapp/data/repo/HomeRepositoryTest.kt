package com.example.aroundegyptapp.data.repo

import com.example.app.com.example.aroundegyptapp.data.model.recommended.Data
import com.example.app.com.example.aroundegyptapp.data.model.respone.BaseResponse
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
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeRepositoryTest {

    private val mockApiService = mockk<ApiService>()
    private var mockRepository: HomeRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockRepository = HomeRepository(mockApiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchExperiences() = runTest {
        val mockApiService: ApiService = mockk()
        val mockResponse = listOf(mockk<Data>(), mockk<Data>())

        coEvery { mockApiService.getExperiences().data } returns mockResponse

        val repo = HomeRepository(mockApiService)
        val result = repo.fetchExperiences()

        assertNotNull(result)
        assertEquals(2, result.size)
    }


    @Test
    fun searchExperience() = runTest {
        val mockApiService: ApiService = mockk()
        val mockResponse = mockk<BaseResponse<Data>>()
        val mockDataList = listOf(mockk<Data>(), mockk<Data>())
        val searchQuery = "luxor"

        every { mockResponse.data } returns mockDataList
        coEvery { mockApiService.searchExperience(searchQuery) } returns mockResponse

        val repo = HomeRepository(mockApiService)
        val result = repo.searchExperience(searchQuery)

        assertNotNull(result)
        assertEquals(2, result.size)
    }


    @Test
    fun likeExperience() = runTest {
        val mockApiService: ApiService = mockk()
        val mockResponse = mockk<LikeExpResp>()
        val id = "7f209d18-36a1-44d5-a0ed-b7eddfad48d6"
        every { mockResponse.data } returns 10
        coEvery { mockApiService.likeExperience(id) } returns mockResponse
        val repo = HomeRepository(mockApiService)
        val result = repo.likeExperience(id)
        assertEquals(10, result)
    }
}