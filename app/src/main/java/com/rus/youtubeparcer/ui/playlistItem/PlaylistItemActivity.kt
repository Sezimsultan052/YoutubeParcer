package com.rus.youtubeparcer.ui.playlistItem

import android.content.Intent
import android.os.Bundle
import com.rus.youtubeparcer.R
import com.rus.youtubeparcer.base.BaseActivity
import com.rus.youtubeparcer.databinding.ActivityPlaylistItemBinding
import com.rus.youtubeparcer.domain.common.Resource
import com.rus.youtubeparcer.ui.playlistItem.adapter.PlaylistItemAdapter
import com.rus.youtubeparcer.ui.video.VideoActivity
import com.rus.youtubeparcer.utils.gone
import com.rus.youtubeparcer.utils.isOnline
import com.rus.youtubeparcer.utils.showToast
import com.rus.youtubeparcer.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistItemActivity :
    BaseActivity<ActivityPlaylistItemBinding>(ActivityPlaylistItemBinding::inflate),
    PlaylistItemAdapter.SendVideoId {
    private val viewModelItem: PlaylistItemViewModel by viewModel()
    private lateinit var adapter: PlaylistItemAdapter

    override fun chekInternet() {
        if (isOnline()) {
            setupUI()
            setupObserver()
        } else {
            showToast("No Internet connection")
            binding.checkInternet.root.visible()
        }
    }

    private fun getData() {
        val extras: Bundle? = intent.extras
        val id = extras?.getString("playlistId")
        id?.let { viewModelItem.getPlaylistVideo(it) }

    }


    private fun setupUI() {
        binding.txtMainTitle.text = getString(R.string.title)
        adapter = PlaylistItemAdapter(this)
        binding.rvPlayer.adapter = adapter
        getData()
    }

    private fun setupObserver() {
        viewModelItem.liveData.observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.items?.let { it1 -> adapter.setPlaylist(it1) }
                    binding.progress.gone()
                }
                Resource.Status.LOADING -> binding.progress.visible()
                Resource.Status.ERROR -> {
                    binding.progress.gone()
                    showToast(it.message + " ")
                }
            }
        }
    }

    override fun sendVideoId(videoId: String, title: String, des: String) {
        if (videoId.isNotBlank()) {
            val intent = Intent(this, VideoActivity::class.java)
            intent.putExtra("videoId", videoId)
            intent.putExtra("title", title)
            intent.putExtra("des", des)
            startActivity(intent)
        }
    }
}
