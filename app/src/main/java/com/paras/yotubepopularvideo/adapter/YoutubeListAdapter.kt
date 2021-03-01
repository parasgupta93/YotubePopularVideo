package com.paras.yotubepopularvideo.adapter
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.paras.yotubepopularvideo.R
import com.paras.yotubepopularvideo.data.remote.Video
import com.paras.yotubepopularvideo.databinding.VideoItemLayoutBinding
import com.paras.yotubepopularvideo.ui.PlayerActivity


class YoutubeListAdapter : RecyclerView.Adapter<YoutubeListAdapter.YoutubeListViewHolder>() {

    private val videoList : ArrayList<Video> = ArrayList()

    fun setList(list : List<Video>){
        Log.i("Paras","setlist"+list.size)
        val start = videoList.size
        videoList.addAll(list)
        notifyItemRangeInserted(start, videoList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeListViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val  binding : ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.video_item_layout,parent,false)
        return YoutubeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YoutubeListViewHolder, position: Int) {
        Log.i("Paras","onBindViewHolder"+position)
        val id = videoList[position].id
        holder.bind(videoList[position])
        setAnimation(holder.itemView)
        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context, PlayerActivity::class.java)
            i.putExtra("video_id", id)
            holder.itemView.context.startActivity(i)
        }
    }

    private fun setAnimation(viewToAnimate: View) {
        val animation: Animation =
            AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.layout_animation)
        viewToAnimate.startAnimation(animation)

    }
    override fun getItemCount(): Int {
        return videoList.size
    }

    inner class YoutubeListViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        private val binding : VideoItemLayoutBinding = binding as VideoItemLayoutBinding

        fun bind(video: Video){
            binding.video =video
            binding.executePendingBindings()
        }

    }

}