package com.cricheroestest.ui.comments.adapter

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cricheroestest.R
import com.cricheroestest.data.network.response.ReplyList
import com.cricheroestest.databinding.ItemChildLayoutBinding

class CommentsChildAdapter(val context: Context, private val childReplyList: List<ReplyList>) :
    RecyclerView.Adapter<CommentsChildAdapter.ChildViewHolder>() {

    inner class ChildViewHolder(var binding: ItemChildLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(replyData: ReplyList) {
            binding.replyData = replyData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = DataBindingUtil.inflate<ItemChildLayoutBinding>(
            LayoutInflater.from(parent.context), R.layout.item_child_layout, parent, false
        )
        return ChildViewHolder(view)
    }

    override fun getItemCount(): Int {
        return childReplyList.size
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val replyData = childReplyList[position]
        if (replyData.totalReaction!! > 0) {
            holder.binding.tvTotalReaction.visibility = View.VISIBLE
        }

        if (replyData.tagUser?.isNotEmpty() == true) {

            val comment = replyData.comment

            // Replace player ID with name if it exists in the tag_user list
            val builder = SpannableStringBuilder()
            val regex = Regex("@(\\d+)")

            var currentIndex = 0
            regex.findAll(comment ?: "").forEach { matchResult ->
                // Append the text before the match
                builder.append(comment?.substring(currentIndex, matchResult.range.first) ?: "")
                // Check if the match corresponds to a player name
                val playerId = matchResult.groupValues[1]
                val tagUser = replyData.tagUser.find { it.playerId == playerId }
                if (tagUser != null) {
                    val playerName = "@${tagUser.name}"
                    // Create a SpannableString with blue color for the player's name
                    val spannableString = SpannableString(playerName)
                    val blueColor = ContextCompat.getColor(context, R.color.blue)
                    val colorSpan = ForegroundColorSpan(blueColor)
                    spannableString.setSpan(
                        colorSpan, 0, playerName.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    // Append the spannable string to the builder
                    builder.append(spannableString)
                } else {
                    // Append the original match value if no player name found
                    builder.append(matchResult.value)
                }
                // Update the current index for the next iteration
                currentIndex = matchResult.range.last + 1
            }
            // Append any remaining text after the last match
            builder.append(comment?.substring(currentIndex) ?: "")

            holder.binding.tvUserComment.text = builder
        }


        if (replyData.isLiked == 1 || replyData.totalLike!! > 0) {
            holder.binding.imageLike.visibility = View.VISIBLE
        }
        if (replyData.isWellplayed == 1 || replyData.totalWellplay!! > 0) {
            holder.binding.imageWellPlayed.visibility = View.VISIBLE
        }
        if (replyData.isLoved == 1 || replyData.totalLove!! > 0) {
            holder.binding.imageLove.visibility = View.VISIBLE
        }
        if (replyData.isRespected == 1 || replyData.totalRespect!! > 0) {
            holder.binding.imageRespect.visibility = View.VISIBLE
        }
        if (replyData.isSad == 1 || replyData.totalSad!! > 0) {
            holder.binding.imageSad.visibility = View.VISIBLE
        }
        if (replyData.isWow == 1 || replyData.totalWow!! > 0) {
            holder.binding.imageWow.visibility = View.VISIBLE
        }



        holder.bind(replyData = replyData)

    }


}