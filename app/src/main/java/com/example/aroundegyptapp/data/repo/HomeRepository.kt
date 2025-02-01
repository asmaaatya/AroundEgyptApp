package com.example.aroundegyptapp.data.repo

import com.example.app.com.example.aroundegyptapp.data.model.recommended.Data
import com.example.aroundegyptapp.data.network.ApiService

class HomeRepository (private val apiService: ApiService) {

    suspend fun fetchExperiences(): List<Data> {
        return apiService.getExperiences().data
    }
    suspend fun searchExperience(searchText: String) :List<Data> {
        return apiService.searchExperience(searchText).data
    }
    suspend fun likeExperience(id:String) :Int{
        return apiService.likeExperience(id).data
    }

    }