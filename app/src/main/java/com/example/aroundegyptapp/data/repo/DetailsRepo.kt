package com.example.aroundegyptapp.data.repo

import com.example.aroundegyptapp.data.model.details.DetailsData
import com.example.aroundegyptapp.data.network.ApiService

class DetailsRepo(private val apiService: ApiService) {
    suspend fun getSingleExperience(id: String): DetailsData{
        return  apiService.getSingleExperience(id).data
    }
    suspend fun likeExperience(id:String) :Int{
        return apiService.likeExperience(id).data
    }
}