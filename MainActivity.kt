package com.example.musicplayer

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.SeekBar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.musicplayer.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var player: MediaPlayer? = null
    private var handle = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pickAudioLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    playSelectedAudio(uri)
                }
            }
        }
        binding.gradientBackground.visibility = View.VISIBLE

        binding.btnSelectFile.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "audio/*"
            }
            pickAudioLauncher.launch(intent)
        }

        binding.btnPlay.setOnClickListener {
            player?.let {
                if (!it.isPlaying) {
                    binding.btnPlay.setBackgroundResource(R.drawable.button_pause)
                    it.start()
                    binding.fullTime.text = millisToSec(it.duration)
                    updatePlayer()
                } else {
                    binding.btnPlay.setBackgroundResource(R.drawable.button_play)
                    it.pause()
                }
            }
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) {
                    player?.seekTo(p1)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private fun playSelectedAudio(uri: Uri) {
        player?.release()
        player = MediaPlayer().apply {
            setDataSource(this@MainActivity, uri)
            prepare()
            start()
        }

        binding.seekBar.max = player!!.duration
        binding.fullTime.text = millisToSec(player!!.duration)
        binding.btnPlay.setBackgroundResource(R.drawable.button_pause)

        // get and show image of music
        val albumArt = getAlbumArt(uri)
        albumArt?.let {
            binding.imageView.setImageBitmap(it)
        }

        updatePlayer()
    }

    private fun getAlbumArt(uri: Uri): Bitmap? {
        val retriever = MediaMetadataRetriever()
        return try {
            retriever.setDataSource(this, uri)
            val art = retriever.embeddedPicture
            art?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            retriever.release()
        }
    }


    private fun millisToSec(millis: Int): String {
        val second = (millis / 1000) % 60
        val minutes = (millis / 1000) / 60
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, second)
    }

    private fun updatePlayer() {
        player?.let {
            binding.time.text = millisToSec(it.currentPosition)
            binding.seekBar.progress = it.currentPosition
            if (it.isPlaying) {
                handle.post{ updatePlayer() }
            }
        }
   }


}
