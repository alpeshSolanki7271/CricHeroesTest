package com.cricheroestest.ui.scoring.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cricheroestest.R
import com.cricheroestest.data.network.response.ScoreData

class ScoreAdapter(context: Context, scoreList: ArrayList<ScoreData>) :
    ArrayAdapter<ScoreData>(context, 0, scoreList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listItemView = convertView
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView. 
            listItemView =
                LayoutInflater.from(context).inflate(R.layout.item_score_layout, parent, false)
        }

        val scoreData: ScoreData? = getItem(position)
        val scoreImage = listItemView!!.findViewById<ImageView>(R.id.ic_image)
        val scoreTitle = listItemView.findViewById<TextView>(R.id.tv_title)

        if (scoreData != null) {
            scoreTitle.text = scoreData.title
            scoreImage.setImageResource(scoreData.image)
        }
        return listItemView
    }
}

