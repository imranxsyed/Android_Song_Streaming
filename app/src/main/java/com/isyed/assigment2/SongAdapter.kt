package com.isyed.employeemanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.isyed.assigment2.R
import com.isyed.assigment2.Song
import com.isyed.assigment2.SongViewHolder


class SongAdapter(private var songList: List<Song> = mutableListOf())
                        : RecyclerView.Adapter<SongViewHolder>(){


    /**
     * Function to set the employee list
     */
    fun setSongsList(songList: List<Song>){

        this.songList = songList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        //parent is the view holder
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val myView : View =  inflater.inflate(R.layout.song_layout, parent, false)

        return SongViewHolder(myView)


    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {

        holder.onBind(songList[position])
    }

}