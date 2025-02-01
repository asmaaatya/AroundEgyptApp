package com.example.aroundegyptapp.data.model.details

import com.example.aroundegyptapp.data.model.City
import com.example.aroundegyptapp.data.model.Era
import com.example.aroundegyptapp.data.model.GmapLocation
import com.example.aroundegyptapp.data.model.OpeningHours
import com.example.aroundegyptapp.data.model.Tag
import com.example.aroundegyptapp.data.model.TicketPrice
import com.example.aroundegyptapp.data.model.TranslatedOpeningHours

data class DetailsData(
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
    val likes_no: Int,
    val opening_hours: OpeningHours,
    val period: Any,
    val rating: Int,
    val recommended: Int,
    val reviews: List<Any>,
    val reviews_no: Int,
    val starting_price: Any,
    val tags: List<Tag>,
    val ticket_prices: List<TicketPrice>,
    val title: String,
    val tour_html: String,
    val tour_url: String,
    val tour_xml: String,
    val tours: List<Any>,
    val translated_opening_hours: TranslatedOpeningHours,
    val views_no: Int
)
