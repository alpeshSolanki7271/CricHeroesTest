package com.cricheroestest.util

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cricheroestest.R
import com.cricheroestest.data.network.response.ScoreData
import com.cricheroestest.databinding.BootomSheetLayoutBinding
import com.cricheroestest.ui.scoring.adapter.ScoreAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetDialog() : BottomSheetDialogFragment() {
    private lateinit var binding: BootomSheetLayoutBinding
    private val gridList = ArrayList<ScoreData>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = BootomSheetLayoutBinding.inflate(inflater, container, false)

        gridList.apply {
            add(
                ScoreData(
                    title = "Dropped Catch", R.drawable.ic_dropped_catch
                )
            )
            add(
                ScoreData(
                    title = "Runs Saved/\nMissed", R.drawable.ic_runs_missed_saved
                )
            )
            add(
                ScoreData(
                    title = "Change Keeper", R.drawable.ic_change_wicket_keeper
                )
            )
            add(
                ScoreData(
                    title = "Match Breaks", R.drawable.ic_set_intervals
                )
            )
            add(
                ScoreData(
                    title = "Full Scorecard", R.drawable.ic_full_scorecard
                )
            )
            add(
                ScoreData(
                    title = "Match Settings", R.drawable.ic_setting_gray_20
                )
            )
            add(
                ScoreData(
                    title = "Changes Score", R.drawable.ic_change_wicket_keeper
                )
            )
            add(
                ScoreData(
                    title = "Change Squad", R.drawable.ic_change_playing_squad
                )
            )
        }
        val adapter = context?.let { ScoreAdapter(it, gridList) }
        binding.rvGrid.adapter = adapter
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // used to show the bottom sheet dialog
        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    companion object {
        const val TAG = "ModalBottomSheetDialog"
    }
}