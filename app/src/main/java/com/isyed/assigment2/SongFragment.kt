package com.isyed.assigment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.isyed.employeemanager.SongAdapter
import kotlinx.android.synthetic.main.song_fragment_layout.view.*

class SongFragment() : Fragment() {

    companion object{

        const val SONG_LIST = "com.isyed.assigment2.SONG_FRAGMENT_SONG_LIST"

        fun newInstance(songsList: List<Song> = mutableListOf()) : SongFragment{

            return SongFragment().also {
                val bundle =  Bundle()
//              bundle.putParcelable(SONG_LIST, songsList) TODO

                bundle.putParcelableArrayList(SONG_LIST,ArrayList<Song>(songsList))

                it.arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        //This is our fragment -- Recyclerview
        val songFragmentLayout : View = inflater.inflate(R.layout.song_fragment_layout, container, false)

        arguments?.getParcelableArrayList<Song>(SONG_LIST)?.let {

            //lets create fragment layout manager
            val songRecyclerViewLayoutManager : GridLayoutManager = GridLayoutManager(activity,2 )
            songFragmentLayout.song_fragment_layout_rv_songs.layoutManager = songRecyclerViewLayoutManager

            //lets create and adapter
            val songList : List<Song> = it
            val employeeAdapter = SongAdapter(songList)
            songFragmentLayout.song_fragment_layout_rv_songs.adapter = employeeAdapter

        }

        return songFragmentLayout
    }




}