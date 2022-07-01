package com.rus.youtubeparcer.di

import com.rus.youtubeparcer.ui.playlist.PlaylistViewModel
import com.rus.youtubeparcer.ui.playlistItem.PlaylistItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel { PlaylistItemViewModel(get()) }
}