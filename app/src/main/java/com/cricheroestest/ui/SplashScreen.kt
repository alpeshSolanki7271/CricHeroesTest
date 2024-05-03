package com.cricheroestest.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cricheroestest.R


@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        try {
            val videoView = findViewById<VideoView>(R.id.video_view)
            val video = Uri.parse("android.resource://" + packageName + "/" + R.raw.onboarding)
            videoView.setVideoURI(video)
            videoView.setOnCompletionListener { jump() }
            videoView.start()
        } catch (ex: Exception) {
            jump()
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        jump()
        return true
    }

    private fun jump() {
        if (isFinishing) return
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}