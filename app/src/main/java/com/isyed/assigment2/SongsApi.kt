package com.isyed.assigment2

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Classic
https://itunes.apple.com/search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50

* Pop

https://itunes.apple.com/search?term=pop&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50

* Rock
https://itunes.apple.com/search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50

 */
interface SongsApi {

    @GET(CLASSIC_MUSIC_END_POINT)
    fun getClassicSongs(): Call<SongsResponse>

    @GET(POP_MUSIC_END_POINT)
    fun getPopSongs(): Call<SongsResponse>

    @GET(ROCK_MUSIC_END_POINT)
    fun getRockSongs(): Call<SongsResponse>

    companion object{

        const val CLASSIC_MUSIC_END_POINT = "/search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50"
        const val POP_MUSIC_END_POINT = "/search?term=pop&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50"
        const val ROCK_MUSIC_END_POINT = "/search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50"
        const val BASE_URL = "https://itunes.apple.com"


        fun initRetrofit(): SongsApi {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SongsApi::class.java)

        }
    }
}