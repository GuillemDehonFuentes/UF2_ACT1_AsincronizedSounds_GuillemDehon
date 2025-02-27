package com.example.uf2_act1_asincronizedsounds_guillemdehon

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "AudioPlayerDebug"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonAudio1).setOnClickListener {
            playAudio(R.raw.audio1)
        }

        findViewById<Button>(R.id.buttonAudio2).setOnClickListener {
            playAudio(R.raw.audio2)
        }

        findViewById<Button>(R.id.buttonAudio3).setOnClickListener {
            playAudio(R.raw.audio3)
        }
    }

    private fun playAudio(audioResId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val mediaPlayer = MediaPlayer.create(this@MainActivity, audioResId)
            mediaPlayer.start()

            Log.d(TAG, "Reproduint audio: $audioResId")

            mediaPlayer.setOnCompletionListener {
                mediaPlayer.release()
                Log.d(TAG, "Audio finalitzat: $audioResId")
            }
        }
    }
}