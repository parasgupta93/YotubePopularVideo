package com.paras.yotubepopularvideo.data.remote

data class Snippet(
    val categoryId: String,
    val channelId: String,
    val channelTitle: String,
    val defaultAudioLanguage: String,
    val defaultLanguage: String,
    val description: String,
    val liveBroadcastContent: String,
    val publishedAt: String,
    val tags: List<String>,
    val title: String
)