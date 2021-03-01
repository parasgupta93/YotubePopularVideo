package com.paras.yotubepopularvideo.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paras.yotubepopularvideo.data.remote.YoutubeResponse
import com.paras.yotubepopularvideo.data.service.ApiService
import com.paras.yotubepopularvideo.util.API_KEY
import com.paras.yotubepopularvideo.util.MAX_ITEM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class YoutubeRepository @Inject constructor(private val apiService: ApiService) {

    private val mostViewData: MutableLiveData<YoutubeResponse> = MutableLiveData()

    fun getPopularVideo(token: String) {
        apiService.popularVideos(token, MAX_ITEM, API_KEY).enqueue(object : Callback<YoutubeResponse?> {
            override fun onResponse(
                call: Call<YoutubeResponse?>,
                response: Response<YoutubeResponse?>
            ) {
                Log.d("Paras", "onResponse")
                if (response.isSuccessful) {
                    mostViewData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<YoutubeResponse?>, t: Throwable) {
                Log.d(
                    "Paras",
                    "onFailure" + Thread.currentThread().name
                )
            }
        })
    }

    fun getMostPopularVideoLiveData(): LiveData<YoutubeResponse> {
        return mostViewData
    }

}