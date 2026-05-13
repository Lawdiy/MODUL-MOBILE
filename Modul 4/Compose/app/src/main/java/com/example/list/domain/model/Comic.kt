package com.example.list.domain.model

data class Comic(
    val id: Int,
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