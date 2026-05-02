package com.example.list.domain.model

data class Movie(
    val id: String,
    val title: String,
    val year: String,
    val plots: Map<String, String>,
    val imageResId: Int,
    val browserUrl: String
) {
    fun getPlot(languageCode: String): String {
        return plots[languageCode] ?: plots["en"] ?: ""
    }
}