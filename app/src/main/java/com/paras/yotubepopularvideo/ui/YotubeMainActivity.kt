package com.paras.yotubepopularvideo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paras.yotubepopularvideo.R
import com.paras.yotubepopularvideo.adapter.YoutubeListAdapter
import com.paras.yotubepopularvideo.databinding.ActivityYotubeMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YotubeMainActivity : AppCompatActivity() {

    private lateinit var activityYoutubeMainBinding : ActivityYotubeMainBinding
    private lateinit var viewModel: YoutubeListViewModel
    private lateinit var adapter: YoutubeListAdapter
    private var loading = true
    private var nextPageToken: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[YoutubeListViewModel::class.java]
        activityYoutubeMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_yotube_main)
        setContentView(activityYoutubeMainBinding.root)
        setupAdapter()
        setupObserver()
        setupScrollListener()
    }

     private fun setupAdapter(){
        adapter = YoutubeListAdapter()
        activityYoutubeMainBinding.recycleView.adapter = adapter
    }

   private fun setupObserver() {
        viewModel.getMostPopularVideoLiveData().observe(this, Observer {
            adapter.setList(it.videos)
            nextPageToken = it.nextPageToken
            loading =false
        })

    }

    private fun setupScrollListener() {
        activityYoutubeMainBinding.recycleView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleCount = activityYoutubeMainBinding.recycleView.layoutManager!!.childCount
                val totalCount = activityYoutubeMainBinding.recycleView.layoutManager!!.itemCount
                val firstVisiblePosition =
                    (activityYoutubeMainBinding.recycleView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (!loading && !nextPageToken.isNullOrEmpty()) {
                    if (visibleCount + firstVisiblePosition >= totalCount
                        && firstVisiblePosition >= 0
                        && totalCount >= 10
                    ) {
                        loading = true
                        viewModel.getMostPopularVideo(nextPageToken!!)
                    }
                }
            }
        })
    }
}