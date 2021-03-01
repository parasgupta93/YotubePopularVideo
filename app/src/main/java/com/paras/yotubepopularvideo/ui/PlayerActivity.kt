package com.paras.yotubepopularvideo.ui

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.paras.yotubepopularvideo.R
import com.paras.yotubepopularvideo.databinding.ActivityPlayerBinding
import com.paras.yotubepopularvideo.util.API_KEY


class PlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener,YouTubePlayer.OnFullscreenListener {

    private var player: YouTubePlayer? = null
    private var mVideoId = ""
    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player)
        mVideoId = intent.getStringExtra("video_id")
        binding.player.initialize(API_KEY, this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?, player: YouTubePlayer,
        wasRestored: Boolean
    ) {
        this.player = player
        player.setOnFullscreenListener(this)

        if (!wasRestored) {
            player.loadVideo(mVideoId)
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        error: YouTubeInitializationResult?
    ) {
            if (error!!.isUserRecoverableError()) {
                error.getErrorDialog(this, 1).show()
            } else {
                val message = "Could not load video"
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
    }

    override fun onFullscreen(isFullScreen: Boolean) {
        val playerParams =
            binding.player.layoutParams as LinearLayout.LayoutParams
            playerParams.width = LinearLayout.LayoutParams.MATCH_PARENT
            playerParams.height = LinearLayout.LayoutParams.MATCH_PARENT
    }

    override fun onDestroy() {
        super.onDestroy()
       player?.release()
    }
}
