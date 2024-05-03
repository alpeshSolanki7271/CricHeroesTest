package com.cricheroestest.ui.scoring.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cricheroestest.R
import com.cricheroestest.data.network.response.ReplyList
import com.cricheroestest.databinding.ItemBallBinding
import com.cricheroestest.databinding.ItemChildLayoutBinding

class CricketAdapter(val context: Context, val ballList: List<BallAction>) :
    RecyclerView.Adapter<CricketAdapter.CricketHolder>() {

    inner class CricketHolder(var binding: ItemBallBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CricketHolder {
        val view = DataBindingUtil.inflate<ItemBallBinding>(
            LayoutInflater.from(parent.context), R.layout.item_ball, parent, false
        )
        return CricketHolder(view)
    }

    override fun getItemCount(): Int {
        return ballList.size
    }

    override fun onBindViewHolder(holder: CricketHolder, position: Int) {
        val ballData = ballList[position]
        holder.binding.tvDesc.text = ballData.ballDesc
        val drawable = ContextCompat.getDrawable(context, R.drawable.rounded_background)

        if (position % 2 == 0) {
            drawable?.let {
                DrawableCompat.setTint(
                    it, ContextCompat.getColor(
                        context, R.color.white
                    )
                )
                holder.binding.myTextView.background = it
            }
        } else {
            drawable?.let {
                DrawableCompat.setTint(
                    it, ContextCompat.getColor(
                        context, R.color.yellow
                    )
                )
                holder.binding.myTextView.background = it
            }
        }

        if (ballData.wicket == 1) {
            holder.binding.myTextView.text = "W"
            holder.binding.myTextView.setTextColor(ContextCompat.getColor(context, R.color.white))
            drawable?.let {
                DrawableCompat.setTint(
                    it, ContextCompat.getColor(
                        context, R.color.lightRed
                    )
                )
                holder.binding.myTextView.background = it
            }
        } else {
            holder.binding.myTextView.text = ballData.runs.toString()
        }


    }
}

