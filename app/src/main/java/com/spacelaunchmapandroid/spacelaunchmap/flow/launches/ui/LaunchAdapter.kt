package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spacelaunchmapandroid.spacelaunchmap.R
import com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data.Launch
import com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data.LaunchListSource

class LaunchAdapter(private val launchList: LaunchListSource) :
    RecyclerView.Adapter<LaunchAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

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

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface OnItemClickListener {
        fun OnItemClick(v: View, position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val launchTitle: TextView = itemView.findViewById(R.id.launch_title)
        private val launchDate: TextView = itemView.findViewById(R.id.launch_date)
        private val launchCompany: TextView = itemView.findViewById(R.id.launch_company)

        init {
            itemView.setOnClickListener { v ->
                if (itemClickListener != null) {
                    itemClickListener!!.OnItemClick(v, bindingAdapterPosition)
                }
            }
        }

        fun setData(launch: Launch) {
            launchTitle.text = launch.title
            launchDate.text = launch.date.subSequence(0, 10)
            launchCompany.text = launch.company
        }
    }

}