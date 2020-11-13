package com.isyed.assigment2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * This object represent the JSON object response that will be returned from following

    https://itunes.apple.com/search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50

    https://itunes.apple.com/search?term=pop&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50

    https://itunes.apple.com/search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50
 */
data class SongsResponse(val results: List<Song>){

}

@Parcelize
data class Song(
    val artistName : String,
    val collectionName: String,
    val artworkUrl60 : String,
    val trackPrice : Float,
    val previewUrl : String
):Parcelable