package com.isyed.assigment2

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.isyed.assigment2.R
import com.squareup.picasso.Picasso
import java.lang.Exception


class SongViewHolder(val employeeView:View) : RecyclerView.ViewHolder(employeeView){

    val imgViewSongPic : ImageView = employeeView.findViewById(R.id.song_layout_iv_song_image)
    val tvSongName : TextView = employeeView.findViewById(R.id.song_layout_song_name)
    val tvSongAlbum :TextView = employeeView.findViewById(R.id.song_layout_song_album)
    val tvSongPrice :TextView = employeeView.findViewById(R.id.song_layout_iv_song_price)
    var songUrl : String = ""


    fun onBind(songInfo: Song){

        Picasso.get().load(songInfo.artworkUrl60).into(imgViewSongPic);
        tvSongName.text = songInfo.artistName
        tvSongAlbum.text = songInfo.collectionName
        tvSongPrice.text = """$ ${songInfo.trackPrice}"""
        songUrl = songInfo.previewUrl

        //setting the view holder
        employeeView.setOnClickListener{

            if (NetworkClass.isNetworkAvailable()){
//                val intent = Intent(Intent.ACTION_VIEW,Uri.parse(songUrl))
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(Uri.parse(songUrl), "video/mp4")

                try {
                    employeeView.context.startActivity(intent)
                }catch (exception : Exception){

                    Toast.makeText(employeeView.context,"Not Able to play a video", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(employeeView.context,"Internet Not Available", Toast.LENGTH_SHORT).show()
            }
        }

    }

}


