package com.example.app.com.example.aroundegyptapp.data.model.recommended

import com.example.aroundegyptapp.data.model.City
import com.example.aroundegyptapp.data.model.Era
import com.example.aroundegyptapp.data.model.GmapLocation
import com.example.aroundegyptapp.data.model.OpeningHours
import com.example.aroundegyptapp.data.model.Period
import com.example.aroundegyptapp.data.model.Review
import com.example.aroundegyptapp.data.model.Tag
import com.example.aroundegyptapp.data.model.TicketPrice
import com.example.aroundegyptapp.data.model.TranslatedOpeningHours

data class Data(
    val address: String,
    val audio_url: String,
    val city: City,
    val cover_photo: String,
    val description: String,
    val detailed_description: String,
    val era: Era,
    val experience_tips: List<Any>,
    val famous_figure: String,
    val founded: String,
    val gmap_location: GmapLocation,
    val has_audio: Boolean,
    val has_video: Int,
    val id: String,
    val is_liked: Any,
    val likes_no: Int,
    val opening_hours: OpeningHours,
    val period: Period,
    val rating: Int,
    val recommended: Int,
    val reviews: List<Review>,
    val reviews_no: Int,
    val starting_price: Int,
    val tags: List<Tag>,
    val ticket_prices: List<TicketPrice>,
    val title: String,
    val tour_html: String,
    val translated_opening_hours: TranslatedOpeningHours,
    val views_no: Int
)