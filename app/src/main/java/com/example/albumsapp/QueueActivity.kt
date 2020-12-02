package com.example.albumsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import android.view.View
import androidx.annotation.RequiresApi

class QueueActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, queuedSong)
        val queueListView = findViewById<ListView>(R.id.queueListView)
        queueListView.adapter = adapter
        registerForContextMenu(queueListView)
    }

    override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.remove_menu, menu)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo //Allows us to inherit from the class Adapterview.AdapterCOntextMenuInfo to get the position
        return when (item.itemId) {
            R.id.removeSong -> {
                val song = queuedSong[menuInfo.position]
                queuedSong.removeAt(menuInfo.position) //gets the position and remove
                adapter.notifyDataSetChanged() //Notify the adapter
                Toast.makeText(this, "$song is removed from Queue.", Toast.LENGTH_SHORT).show()
                //Notification that will be fired when the size of the array is == 0
                if (queuedSong.size <= 0) {
                    notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    //Display the notification
                    val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        notificationChannel = NotificationChannel(
                                channelId, description, NotificationManager.IMPORTANCE_HIGH)
                        notificationChannel.enableLights(true)
                        notificationChannel.lightColor = Color.CYAN
                        notificationChannel.enableVibration(false)
                        notificationManager.createNotificationChannel(notificationChannel)

                        builder = Notification.Builder(this, channelId)
                                .setContentTitle("Song Queue")
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentIntent(pendingIntent)
                                .setContentText("Your song queue is empty")
                    } else {
                        builder = Notification.Builder(this)
                                .setContentTitle("Song Queue")
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentIntent(pendingIntent)
                                .setContentText("Your Song Queue is empty")
                    }
                    notificationManager.notify(1234, builder.build())
                }
                true
            }
            else -> {
                return super.onContextItemSelected(item)
            }

        }
    }
}