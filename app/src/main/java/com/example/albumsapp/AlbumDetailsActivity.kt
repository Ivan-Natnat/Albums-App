package com.example.albumsapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class AlbumDetailsActivity : AppCompatActivity() {

    @SuppressLint("Resource Type")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        val viewName = findViewById<TextView>(R.id.albumTitleTextView)
        val viewImage = findViewById<ImageView>(R.id.albumImageView)

        var albumSongs :Array<String> =arrayOf()
        val position = intent.extras!!.getString("position")

        if (position.equals("Sonder Son")){
            viewName.text = position
            viewImage.setImageResource(R.drawable.sonder_son)
            albumSongs = arrayOf( "Let Me Know","Fuck the World",
                "Bluffin",
                "Clouded",
                "Been Away",
                "Sky Line",)
        }
        else if (position.equals("Lost")){
            viewName.text = position
            viewImage.setImageResource(R.drawable.lost)
            albumSongs = arrayOf("Lost Kids Get Money","Soon Az I Get Home","Make It Out",
                "Make Luv", "Skyline",)
        }
        else if (position.equals("Fuck the World")){
            viewName.text = position
            viewImage.setImageResource(R.drawable.ifuck_the_world)
            albumSongs = arrayOf("Trust", "Poison", "Too Fast", "Running on E","Dead Man Walking")
        }
        var adapter = ArrayAdapter(this , android.R.layout.simple_list_item_1 , albumSongs)
        var albumDetailListView = findViewById<ListView>(R.id.albumDetailListView)
        albumDetailListView.adapter = adapter
    }
}