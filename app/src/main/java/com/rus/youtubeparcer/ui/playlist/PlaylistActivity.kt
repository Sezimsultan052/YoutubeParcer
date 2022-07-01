package com.rus.youtubeparcer.ui.playlist

import android.content.Intent
import com.rus.youtubeparcer.base.BaseActivity
import com.rus.youtubeparcer.databinding.ActivityMainBinding
import com.rus.youtubeparcer.domain.common.Resource
import com.rus.youtubeparcer.ui.playlist.adapter.PlaylistAdapter
import com.rus.youtubeparcer.ui.playlistItem.PlaylistItemActivity
import com.rus.youtubeparcer.utils.gone
import com.rus.youtubeparcer.utils.isOnline
import com.rus.youtubeparcer.utils.showToast
import com.rus.youtubeparcer.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    PlaylistAdapter.SendKeyPlayList<String> {
    private lateinit var adapter: PlaylistAdapter
    private val playlistViewModel: PlaylistViewModel by viewModel()
    override fun initListener() {
        super.initListener()
        binding.checkInternet.btnCheckInternet.setOnClickListener {
            chekInternet()
        }
    }

    override fun chekInternet() {
        if (isOnline()) {
            binding.checkInternet.root.gone()
            setupUI()
            setupObserver()
        } else {
            showToast("No Internet connection")
            binding.checkInternet.root.visible()
        }
    }

    private fun setupUI() {
        adapter = PlaylistAdapter(this)
        binding.rvPlaylist.adapter = adapter
    }

    private fun setupObserver() {
        playlistViewModel.liveData.observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progress.gone()
                    it.data?.items?.let { it1 -> adapter.setPlaylist(it1) }
                }
                Resource.Status.LOADING -> binding.progress.visible()
                Resource.Status.ERROR -> showToast(it.message + "")
            }
        }
    }

    override fun onItemClick(data: String) {
        val intent = Intent(this, PlaylistItemActivity::class.java)
        intent.putExtra("playlistId", data)
        startActivity(intent)
    }
}