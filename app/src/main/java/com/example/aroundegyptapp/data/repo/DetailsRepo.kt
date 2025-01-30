package com.example.aroundegyptapp.data.repo

import com.example.aroundegyptapp.data.model.details.Data
import com.example.aroundegyptapp.data.network.ApiService

class DetailsRepo(private val apiService: ApiService) {
    suspend fun getSingleExperience(id: String): Data{
        return  apiService.getSingleExperience(id).data
    }
}