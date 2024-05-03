package com.cricheroestest.ui.scoring

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cricheroestest.R
import com.cricheroestest.databinding.ActivityScoringBinding
import com.cricheroestest.ui.scoring.adapter.BallAction
import com.cricheroestest.ui.scoring.adapter.CricketAdapter
import com.cricheroestest.util.ModalBottomSheetDialog

class ScoringActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoringBinding
    private var ballList = ArrayList<BallAction>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScoringBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.back.setOnClickListener {
            finish()
        }

        binding.quickOptions.setOnClickListener {
            val modal = ModalBottomSheetDialog()
            supportFragmentManager.let { modal.show(it, ModalBottomSheetDialog.TAG) }
        }

        ballList.apply {
            add(BallAction(runs = 1, wicket = 0, ballDesc = ""))
            add(BallAction(runs = 4, wicket = 0, ballDesc = ""))
            add(BallAction(runs = 1, wicket = 0, ballDesc = "WD"))
            add(BallAction(runs = 1, wicket = 0, ballDesc = "NB"))
            add(BallAction(runs = 1, wicket = 0, ballDesc = ""))
            add(BallAction(runs = 0, wicket = 0, ballDesc = "LB"))
            add(BallAction(runs = 1, wicket = 1, ballDesc = ""))
            add(BallAction(runs = 1, wicket = 0, ballDesc = ""))
            add(BallAction(runs = 4, wicket = 0, ballDesc = ""))
            add(BallAction(runs = 1, wicket = 0, ballDesc = "WD"))
            add(BallAction(runs = 1, wicket = 0, ballDesc = "NB"))
        }

        val adapter = CricketAdapter(context = this, ballList = ballList)
        binding.rvScore.adapter = adapter

    }
}


