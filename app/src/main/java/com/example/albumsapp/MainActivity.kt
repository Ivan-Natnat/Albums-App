package com.example.albumsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
val productsArray = arrayOf(
    "Rehab(Winter in Paris)",
    "Let Me Know","Dead Man Walking",
    "Bluffin",
    "Clouded",
    "Been Away",
    "Sky Line",
    "Lost Kids Get Money",
    "Soon Az I Get Home",
    "Fuck The World",
    "Make It Out",
    "Make Luv",
    "Trust",
    "Poison",
    "Too Fast",
    "Running on E"
)
var queuedSong = arrayListOf<String>()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productsArray)
        val songsListView = findViewById<ListView>(R.id.songsListView)
        songsListView.adapter = adapter
        registerForContextMenu(songsListView)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.long_press, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.add_to_queue -> {
                Toast.makeText(this, "Added to Queue", Toast.LENGTH_SHORT).show()
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                queuedSong.add(productsArray[info.position])
                true
            }
            else-> super.onContextItemSelected(item)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.go_to_queue_page -> {
                val intent = Intent(this, QueueActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.go_to_songs -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.go_to_albums -> {
                startActivity(Intent(this, AlbumActivity::class.java))
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }

    }
}