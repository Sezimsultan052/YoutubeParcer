package com.rus.youtubeparcer.di

import com.rus.youtubeparcer.data.repo.PlaylistRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModule: Module = module {
    factory { PlaylistRepository(get()) }


}