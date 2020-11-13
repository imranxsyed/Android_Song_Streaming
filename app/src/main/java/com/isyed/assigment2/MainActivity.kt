package com.isyed.assigment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.get
import com.google.android.material.tabs.TabLayout
import com.isyed.employeemanager.SongAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.song_fragment_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val ROCK_TAB   = "rock"
const val CLASSIC_TAB = "classic"
const val POP_TAB = "pop"


class  MainActivity : AppCompatActivity(),TabLayout.OnTabSelectedListener, Callback<SongsResponse> {



    private var rockMusicSongs : List<Song> = mutableListOf()
    private var classicMusicSongs : List<Song> = mutableListOf()
    private var popMusicSongs : List<Song> = mutableListOf()
    private val songFragment = SongFragment.newInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setting the fragment (ONE TIME ONLY -- Change the adapter afterwards to show differnt results)
        supportFragmentManager.beginTransaction()
            .add(R.id.main_activity_fragment_container,songFragment)
            .commit()



        //tabs click listeners
        song_tabs.addOnTabSelectedListener(this)

        //selecting the first tab so it could populate the date initially
        val initTab = song_tabs.getTabAt(song_tabs.selectedTabPosition)
        onTabSelected(initTab)



    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {

        //make sure internet is available
        if(!NetworkClass.isNetworkAvailable()){
            Toast.makeText(this, "Internet Not available!", Toast.LENGTH_LONG).show()
            return
        }

        val tabName = tab?.text
        tabName?.let{
            if (ROCK_TAB.equals(it.toString(),true)){

                if(rockMusicSongs.isEmpty()){
                    SongsApi.initRetrofit().getRockSongs().enqueue(this)
                }
                else{
                    (songFragment.song_fragment_layout_rv_songs.adapter as SongAdapter).setSongsList(rockMusicSongs)
                }

            }
            else if (CLASSIC_TAB.equals(it.toString(),true)){


                if(classicMusicSongs.isEmpty()){
                    SongsApi.initRetrofit().getClassicSongs().enqueue(this)
                }
                else{
                    (songFragment.song_fragment_layout_rv_songs.adapter as SongAdapter).setSongsList(classicMusicSongs)
                }
            }
            else if (POP_TAB.equals(it.toString(),true)){

                if(popMusicSongs.isEmpty()){
                    SongsApi.initRetrofit().getPopSongs().enqueue(this)
                }
                else{
                    (songFragment.song_fragment_layout_rv_songs.adapter as SongAdapter).setSongsList(popMusicSongs)
                }
            }
        }

    }

    override fun onFailure(call: Call<SongsResponse>, t: Throwable) {
        Toast.makeText(this@MainActivity,
            "Request Failure!",
            Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<SongsResponse>, response: Response<SongsResponse>) {


        if(response.isSuccessful){
            response.body()?.let {
                 val requestUrl : String? = response.headers().get("x-apple-orig-url")
                 requestUrl?.let{link->
                     //set the classic music list
                     if (link.contains(SongsApi.CLASSIC_MUSIC_END_POINT)){
                         classicMusicSongs = it.results
                         (songFragment.song_fragment_layout_rv_songs.adapter as SongAdapter).setSongsList(classicMusicSongs)
                     }
                     else if (link.contains(SongsApi.POP_MUSIC_END_POINT)){
                         popMusicSongs = it.results
                         (songFragment.song_fragment_layout_rv_songs.adapter as SongAdapter).setSongsList(popMusicSongs)
                     }
                     else if (link.contains(SongsApi.ROCK_MUSIC_END_POINT)){
                         rockMusicSongs = it.results
                         (songFragment.song_fragment_layout_rv_songs.adapter as SongAdapter).setSongsList(rockMusicSongs)
                     }

                 }

            }
        }
        else{
            Toast.makeText(this@MainActivity,
                "Unable to retrieve data from server!",
                Toast.LENGTH_SHORT).show()
        }
    }


}