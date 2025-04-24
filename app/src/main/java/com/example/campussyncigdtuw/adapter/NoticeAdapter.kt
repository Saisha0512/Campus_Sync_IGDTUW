package com.example.campussyncigdtuw.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.campussyncigdtuw.R
import com.example.campussyncigdtuw.model.Notice
import java.io.File


class NoticeAdapter(
    private val context: Context,
    private val notices: MutableList<Notice>,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {


    class NoticeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.noticeTitle)
        val timeText: TextView = view.findViewById(R.id.noticeTimestamp)
        val imageView: ImageView = view.findViewById(R.id.noticeImageView)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notice_item_layout, parent, false)
        return NoticeViewHolder(view)
    }


    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val notice = notices[position]
        holder.titleText.text = notice.title
        holder.timeText.text = notice.timestamp
        holder.imageView.setImageBitmap(notice.imageBitmap)


        holder.deleteButton.setOnClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("Delete Notice")
                setMessage("Are you sure you want to delete this notice?")
                setPositiveButton("Yes") { _, _ ->
                    // Rotate + Fade Out Animation
                    val rotate = RotateAnimation(
                        0f, 360f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                    ).apply {
                        duration = 500
                    }
                    val fadeOut = AlphaAnimation(1f, 0f).apply {
                        duration = 500
                    }


                    holder.itemView.startAnimation(rotate)
                    holder.itemView.startAnimation(fadeOut)


                    holder.itemView.postDelayed({
                        val filesDir = File(context.filesDir, "notices")
                        val imageFile = File(filesDir, "${notice.filename}.jpg")
                        val titleFile = File(filesDir, "${notice.filename}.txt")
                        imageFile.delete()
                        titleFile.delete()


                        notices.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, notices.size)


                        onDelete(position)
                    }, 500)
                }
                setNegativeButton("Cancel", null)
                show()
            }
        }
    }


    override fun getItemCount(): Int = notices.size
}
