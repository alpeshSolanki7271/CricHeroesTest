package com.cricheroestest.ui.comments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.cricheroestest.R
import com.cricheroestest.paging.CommentsPagingAdapter
import com.cricheroestest.databinding.ActivityCommentsBinding
import com.cricheroestest.util.MyLiveData
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CommentsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommentsBinding
    private lateinit var viewModel: CommentsViewModel
    private lateinit var commentsPagingAdapter: CommentsPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(owner = this)[CommentsViewModel::class.java]

        MyLiveData.myData.observe(this@CommentsActivity) { pageX ->
            if (pageX.next != null) {
                binding.tvPrevious.visibility = View.VISIBLE
            } else {
                binding.tvPrevious.visibility = View.GONE
            }
        }

        binding.icBack.setOnClickListener { finish() }

        // Message character count
        val txWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.tvCounter.text = s.length.toString()
            }

            override fun afterTextChanged(s: Editable) {}
        }
        binding.tvMessage.addTextChangedListener(txWatcher)

        commentsPagingAdapter = CommentsPagingAdapter(this)
        viewModel.commentsList.observe(this) {
            if (it != null) {
                commentsPagingAdapter.submitData(lifecycle, it)
                binding.parentRecyclerView.apply {
                    hasFixedSize()
                    adapter = commentsPagingAdapter
                }
            } else {
                binding.tvNo.visibility = View.VISIBLE
                binding.parentRecyclerView.visibility = View.GONE
            }
        }
    }
}
