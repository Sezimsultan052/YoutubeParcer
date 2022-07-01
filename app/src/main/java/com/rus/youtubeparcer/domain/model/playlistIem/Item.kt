package com.rus.youtubeparcer.domain.model.playlistIem

data class Item(
    val contentDetails: ContentDetails,
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet
)