package com.rus.youtubeparcer.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.rus.youtubeparcer.data.network.ApiService
import com.rus.youtubeparcer.domain.common.Resource
import com.rus.youtubeparcer.domain.model.playlist.PlayList
import com.rus.youtubeparcer.domain.model.playlistIem.PlaylistVideos
import kotlinx.coroutines.Dispatchers

class PlaylistRepository(private val api: ApiService) {

    fun getPlaylists(): LiveData<Resource<PlayList?>> = liveData(Dispatchers.IO) {
        val result = api.getPlaylists()
        emit(Resource.loading())
        if (result.isSuccessful) {
            emit(Resource.success(result.body()))
        } else {
            emit(Resource.error(result.message()))
        }
    }

    fun getPlaylistItem(id: String): LiveData<Resource<PlaylistVideos?>> =
        liveData(Dispatchers.IO) {
            val result = api.getPlaylistItems(playlistId = id)
            emit(Resource.loading())
            if (result.isSuccessful) {
                emit(Resource.success(result.body()))
            } else {
                emit(Resource.error(result.message()))
            }
        }
}