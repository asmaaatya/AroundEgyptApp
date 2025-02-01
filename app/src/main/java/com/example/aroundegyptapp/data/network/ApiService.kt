package com.example.aroundegyptapp.data.network

import com.example.app.com.example.aroundegyptapp.data.model.recommended.Data
import com.example.app.com.example.aroundegyptapp.data.model.respone.BaseResponse
import com.example.aroundegyptapp.data.model.details.DetailsResp
import com.example.aroundegyptapp.data.model.like.LikeExpResp
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("experiences?filter[recommended]=true")
    suspend fun getRecommendedExperience() : BaseResponse<Data>
    @GET("experiences")
    suspend fun getExperiences() : BaseResponse<Data>
    @GET("experiences")
    suspend fun searchExperience(@Query("filter[title]") searchText: String) : BaseResponse<Data>
    @GET("experiences/{id}")
    suspend fun getSingleExperience(@Path("id") id: String) :DetailsResp
    @POST("experiences/{id}/like")
    suspend fun likeExperience(@Path("id") id: String) : LikeExpResp
}