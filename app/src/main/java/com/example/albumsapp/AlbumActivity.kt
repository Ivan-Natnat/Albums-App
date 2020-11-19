package com.example.albumsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView

class AlbumActivity : AppCompatActivity() {

    var names = arrayOf("Lost" , "Fuck The World" , "Sonder Son")
    var images = intArrayOf(R.drawable.lost , R.drawable.ifuck_the_world , R.drawable.sonder_son)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        val gridView = findViewById<GridView>(R.id.albumGView)
        val mainAdapter = MainAdapter(this , names , images)
        gridView.adapter = mainAdapter
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, id ->
            val intent = Intent(this, AlbumDetailsActivity::class.java)
            intent.putExtra("position", names[position])
            startActivity(intent)
        }
    }
}