package com.example.campussyncigdtuw

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ScheduleAdapter(private var scheduleList: List<ScheduleItem>) :
    RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_schedule_item_tt, parent, false)
        return ScheduleViewHolder(view)
    }


    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(scheduleList[position])
    }


    override fun getItemCount(): Int {
        return scheduleList.size
    }


    // `updateData` function to update the RecyclerView's data
    fun updateData(newData: List<ScheduleItem>) {
        scheduleList = newData
        notifyDataSetChanged() // Refreshes the entire list; can be optimized for specific updates
    }


    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val periodTextView: TextView = itemView.findViewById(R.id.item_period)
        private val facultyTextView: TextView = itemView.findViewById(R.id.item_faculty)
        private val topicTextView: TextView = itemView.findViewById(R.id.item_topic)


        fun bind(scheduleItem: ScheduleItem) {
            periodTextView.text = scheduleItem.period
            facultyTextView.text = scheduleItem.faculty
            topicTextView.text = scheduleItem.topic
        }
    }
}
