package com.cricheroestest.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cricheroestest.R
import com.cricheroestest.ui.comments.CommentsActivity
import com.cricheroestest.databinding.ActivityMainBinding
import com.cricheroestest.ui.scoring.ScoringActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnComments.setOnClickListener {
            val intent = Intent(this@MainActivity, CommentsActivity::class.java)
            startActivity(intent)
        }
        binding.btnScoring.setOnClickListener {
            val intent = Intent(this@MainActivity, ScoringActivity::class.java)
            startActivity(intent)
        }


    }
}