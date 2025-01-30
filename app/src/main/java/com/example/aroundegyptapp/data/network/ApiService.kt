package com.example.aroundegyptapp.data.network

import com.example.aroundegyptapp.data.model.RecommendedResp
import com.example.aroundegyptapp.data.model.details.Data
import com.example.aroundegyptapp.data.model.details.SingleExperienceResp
import com.example.aroundegyptapp.data.model.like.LikeExpResp
import com.example.aroundegyptapp.data.model.recent.MostRecentResp
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("experiences?filter[recommended]=true")
    suspend fun getRecommendedExperience() : RecommendedResp
    @GET("experiences")
    suspend fun getMostRecentExperience() : MostRecentResp
    @GET("experiences?filter[title]={search_text}")
    suspend fun searchExperience(@Query ("search_text") search_text: String)
    @GET("experiences/{id}")
    suspend fun getSingleExperience(@Path("id") id: String) :SingleExperienceResp
    @POST("experiences/{id}/like")
    suspend fun likeExperience(@Path("id") id: String) : LikeExpResp
}