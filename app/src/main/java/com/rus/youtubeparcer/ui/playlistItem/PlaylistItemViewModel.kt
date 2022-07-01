package com.rus.youtubeparcer.ui.playlistItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rus.youtubeparcer.data.repo.PlaylistRepository
import com.rus.youtubeparcer.domain.common.Resource
import com.rus.youtubeparcer.domain.model.playlistIem.PlaylistVideos

class PlaylistItemViewModel(private val repo: PlaylistRepository) : ViewModel() {
    var liveData: LiveData<Resource<PlaylistVideos?>> = MutableLiveData()

    fun getPlaylistVideo(id: String) {
        liveData = repo.getPlaylistItem(id)
    }
}