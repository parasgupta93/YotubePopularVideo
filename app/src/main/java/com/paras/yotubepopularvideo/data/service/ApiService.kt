package com.paras.yotubepopularvideo.data.service

import com.paras.yotubepopularvideo.data.remote.YoutubeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v3/videos?part=snippet%2CcontentDetails%2Cstatistics&chart=mostPopular&regionCode=IN")
     fun popularVideos(@Query("pageToken") pageToken: String, @Query("maxResults") maxResults: Int ,@Query("key") key: String) : Call<YoutubeResponse>
}