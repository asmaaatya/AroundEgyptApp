package com.example.aroundegyptapp.data.model.details

import com.example.aroundegyptapp.data.model.Meta
import com.example.aroundegyptapp.data.model.Pagination

data class DetailsResp(
    val `data`: DetailsData,
    val meta: Meta,
    val pagination: Pagination
)