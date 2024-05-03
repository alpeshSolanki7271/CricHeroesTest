package com.cricheroestest.paging

import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cricheroestest.R
import com.cricheroestest.data.network.response.CommentsList
import com.cricheroestest.databinding.ItemParentCommentsLayoutBinding
import com.cricheroestest.ui.comments.CommentsActivity
import com.cricheroestest.ui.comments.adapter.CommentsChildAdapter

class CommentsPagingAdapter(val context: CommentsActivity) :
    PagingDataAdapter<CommentsList, CommentsPagingAdapter.ViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(commentsList = item)

            if (item.tagUser?.isNotEmpty() == true) {
                val comment = item.comment
                // Replace player ID with name if it exists in the tag_user list
                val builder = SpannableStringBuilder()
                val regex = Regex("@(\\d+)")

                var currentIndex = 0
                regex.findAll(comment ?: "").forEach { matchResult ->
                    // Append the text before the match
                    builder.append(comment?.substring(currentIndex, matchResult.range.first) ?: "")
                    // Check if the match corresponds to a player name
                    val playerId = matchResult.groupValues[1]
                    val tagUser = item.tagUser.find { it.playerId == playerId }
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
            } else {
                holder.binding.tvUserComment.text = item.comment
            }


            if (item.reply?.data?.isNotEmpty() == true) {
                val childAdapter =
                    CommentsChildAdapter(context = context, childReplyList = item.reply.data)
                holder.binding.replyRv.adapter = childAdapter
            } else {
                holder.binding.replyRv.visibility = View.GONE
            }

            if (item.totalReply == 1) {
                holder.binding.tvReplies.visibility = View.VISIBLE
                holder.binding.icDot.visibility = View.VISIBLE
                holder.binding.tvReplies.text = ContextCompat.getString(context, R.string.str_reply)
            } else if (item.totalReply!! > 1) {
                holder.binding.tvReplies.visibility = View.VISIBLE
                holder.binding.icDot.visibility = View.VISIBLE
                holder.binding.tvReplies.text =
                    ContextCompat.getString(context, R.string.str_replies)
            } else {
                holder.binding.tvReplies.visibility = View.GONE
                holder.binding.icDot.visibility = View.GONE
                holder.binding.tvTotalReplies.visibility = View.GONE
            }

            if (item.totalReaction!! > 0) {
                holder.binding.tvTotalReaction.visibility = View.VISIBLE
            }

            if (item.isLiked == 1 || item.totalLike!! > 0) {
                holder.binding.imageLike.visibility = View.VISIBLE
            }
            if (item.isWellplayed == 1 || item.totalWellplay!! > 0) {
                holder.binding.imageWellPlayed.visibility = View.VISIBLE
            }
            if (item.isLoved == 1 || item.totalLove!! > 0) {
                holder.binding.imageLove.visibility = View.VISIBLE
            }
            if (item.isRespected == 1 || item.totalRespect!! > 0) {
                holder.binding.imageRespect.visibility = View.VISIBLE
            }
            if (item.isSad == 1 || item.totalSad!! > 0) {
                holder.binding.imageSad.visibility = View.VISIBLE
            }
            if (item.isWow == 1 || item.totalWow!! > 0) {
                holder.binding.imageWow.visibility = View.VISIBLE
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = DataBindingUtil.inflate<ItemParentCommentsLayoutBinding>(
            LayoutInflater.from(parent.context), R.layout.item_parent_comments_layout, parent, false
        )

        return ViewHolder(view)
    }

    class ViewHolder(var binding: ItemParentCommentsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(commentsList: CommentsList) {
            binding.commentsData = commentsList
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CommentsList>() {
            override fun areItemsTheSame(oldItem: CommentsList, newItem: CommentsList): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CommentsList, newItem: CommentsList): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}