package com.example.aroundegyptapp.home


import com.example.aroundegyptapp.data.repo.HomeRepository
import com.example.aroundegyptapp.ui.theme.home.HomeViewModel
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
@ExperimentalCoroutinesApi
class HomeViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun likeExperience() = runTest {
        val repository = mockk<HomeRepository>(relaxed = true)
        val viewModel = HomeViewModel(repository)
        val id="7f209d18-36a1-44d5-a0ed-b7eddfad48d6"
        viewModel.likeExperience(id)

        testDispatcher.scheduler.advanceUntilIdle()

        val likesMap = viewModel.likesMap.value
        assertTrue(likesMap.containsKey(id))
    }

    @Test
    fun getRecommended() {
        val viewModel = HomeViewModel(mockk())
        val recommended = viewModel.recommended.value
        assertNotNull(recommended)
    }

    @Test
    fun getRecent() {
        val viewModel = HomeViewModel(mockk())
        val recent = viewModel.recent.value
        assertNotNull(recent)
    }

    @Test
    fun executeSearch() {
        val viewModel = HomeViewModel(mockk())
        viewModel.executeSearch("luxor")
        val searchQuery = viewModel.searchResult.value
        assertNotNull(searchQuery)
    }

    @Test
    fun clearSearch() {
        val viewModel = HomeViewModel(mockk())
        viewModel.clearSearch()
        val searchQuery = viewModel.searchResult.value
        assertTrue(searchQuery.isEmpty())

    }
}