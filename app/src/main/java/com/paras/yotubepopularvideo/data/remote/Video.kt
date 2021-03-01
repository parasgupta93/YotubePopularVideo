package com.paras.yotubepopularvideo.data.remote



data class Video(
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet,
    val statistics: Statistics
)