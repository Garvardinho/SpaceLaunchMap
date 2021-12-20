package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spacelaunchmapandroid.spacelaunchmap.R
import com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data.Launch
import com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data.LaunchListSource

class LaunchAdapter(private val launchList: LaunchListSource) :
    RecyclerView.Adapter<LaunchAdapter.ViewHolder>() {

    private var onMoreInfoButtonClickListener: OnMoreInfoButtonClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.launch_info_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(launchList.getCardData(position))
    }

    override fun getItemCount(): Int {
        return launchList.getSize()
    }

    fun setOnItemClickListener(onMoreInfoButtonClickListener: OnMoreInfoButtonClickListener) {
        this.onMoreInfoButtonClickListener = onMoreInfoButtonClickListener
    }

    interface OnMoreInfoButtonClickListener {
        fun onMoreInfoButtonClick(v: View, position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val launchTitle: TextView = itemView.findViewById(R.id.launch_title)
        private val launchDate: TextView = itemView.findViewById(R.id.launch_date)
        private val launchLocation: TextView = itemView.findViewById(R.id.launch_location)
        private val launchCompany: TextView = itemView.findViewById(R.id.launch_company)
        private val launchMoreInfoButton: ImageButton = itemView.findViewById(R.id.launch_more_info_button)

        fun setData(launch: Launch) {
            launchTitle.text = launch.title
            launchDate.text = launch.date.subSequence(0, 10)
            launchLocation.text = launch.location
            launchCompany.text = launch.company
        }

        init {
            launchMoreInfoButton.setOnClickListener { v ->
                if (onMoreInfoButtonClickListener != null) {
                    onMoreInfoButtonClickListener!!.onMoreInfoButtonClick(v, bindingAdapterPosition)
                }
            }
        }
    }

}