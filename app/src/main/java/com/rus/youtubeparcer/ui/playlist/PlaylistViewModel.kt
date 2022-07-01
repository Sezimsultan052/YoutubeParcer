package com.rus.youtubeparcer.ui.playlist

import androidx.lifecycle.ViewModel
import com.rus.youtubeparcer.data.repo.PlaylistRepository

class PlaylistViewModel(repo: PlaylistRepository) : ViewModel() {
    val liveData = repo.getPlaylists()
}