package com.paras.yotubepopularvideo.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.paras.yotubepopularvideo.data.YoutubeRepository
import com.paras.yotubepopularvideo.data.remote.YoutubeResponse

class YoutubeListViewModel  @ViewModelInject constructor(private val repository: YoutubeRepository): ViewModel() {

    init {
        Log.d("Paras","init")
        repository.getPopularVideo("")
    }

    fun getMostPopularVideoLiveData() : LiveData<YoutubeResponse> {
        return repository.getMostPopularVideoLiveData()
    }

    fun getMostPopularVideo(token: String){
        repository.getPopularVideo(token)
    }

}