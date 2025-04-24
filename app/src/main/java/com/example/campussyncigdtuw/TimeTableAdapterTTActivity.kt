package com.example.campussyncigdtuw

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TimetableAdapter(private val items: MutableList<ScheduleItem>) :
    RecyclerView.Adapter<TimetableAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val periodTextView: TextView = view.findViewById(R.id.item_period)
        val facultyTextView: TextView = view.findViewById(R.id.item_faculty)
        val topicTextView: TextView = view.findViewById(R.id.item_topic)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_schedule_item_tt, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.periodTextView.text = item.period
        holder.facultyTextView.text = item.faculty
        holder.topicTextView.text = item.topic
    }


    override fun getItemCount(): Int = items.size


    fun addUpdatedData(period: String?, faculty: String?, topic: String?) {
        val newItem = ScheduleItem(period ?: "", faculty ?: "", topic ?: "")
        items.add(newItem)
        notifyItemInserted(items.size - 1) // Notify about the new item
    }


    fun updateData(newItems: List<ScheduleItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged() // Refresh entire dataset (use specific notifications if possible)
    }
}
