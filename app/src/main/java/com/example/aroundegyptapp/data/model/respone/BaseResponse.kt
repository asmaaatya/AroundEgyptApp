package com.example.app.com.example.aroundegyptapp.data.model.respone

import com.example.aroundegyptapp.data.model.Meta
import com.example.aroundegyptapp.data.model.Pagination

data class BaseResponse<T>(
    val data: List<T>,
    val meta: Meta,
    val pagination: Pagination
    )