package com.paras.yotubepopularvideo.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class YoutubeResponse(
    val etag: String,
    @SerializedName("items")
    @Expose
    val videos: List<Video>,
    val kind: String,
    val nextPageToken: String
)