package com.example.aroundegyptapp.data.model.recent

data class MostRecentResp(
    val `data`: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)