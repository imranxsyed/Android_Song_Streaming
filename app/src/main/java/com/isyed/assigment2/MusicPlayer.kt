package com.isyed.assigment2

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

interface MusicPlayer{

    companion object{

        fun playMusic(songUrl: String){

//            val intent = Intent(Intent.ACTION_VIEW).apply {
//                data = songUrl
//            }
//            if (intent.resolveActivity(packageManager) != null) {
//                startActivity(intent)
//            }

        }
    }
}