package com.example.campussyncigdtuw

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campussyncigdtuw.adapter.NoticeAdapter
import com.example.campussyncigdtuw.model.Notice
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class ViewNoticeActivity : AppCompatActivity() {


    private lateinit var noticeRecyclerView: RecyclerView
    private lateinit var backButton: ImageButton
    private val notices = mutableListOf<Notice>()
    private lateinit var adapter: NoticeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_notice)


        // Initialize Views
        noticeRecyclerView = findViewById(R.id.noticeRecyclerView)
        backButton = findViewById(R.id.viewNoticeBackButton)


        // Setup Toolbar
        val toolbar: Toolbar = findViewById(R.id.viewNoticeToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        // Setup Back Navigation
        backButton.setOnClickListener {
            startActivity(Intent(this, NoticeBoardActivity::class.java))
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            finish()
        }


        // Setup RecyclerView
        // Initialize Adapter
        adapter = NoticeAdapter(this, notices) {
            Snackbar.make(noticeRecyclerView, "Notice Deleted", Snackbar.LENGTH_SHORT).show()
        }
        noticeRecyclerView.layoutManager = LinearLayoutManager(this)
        noticeRecyclerView.adapter = adapter


        loadNotices()
    }


    private fun loadNotices() {
        try {
            val noticeDir = File(filesDir, "notices")
            if (noticeDir.exists() && noticeDir.isDirectory) {
                val files = noticeDir.listFiles()


                files?.sortedByDescending { it.lastModified() }?.forEach { file ->
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    if (bitmap != null) {
                        val titleFile = File(noticeDir, "${file.nameWithoutExtension}.txt")
                        val title = if (titleFile.exists()) {
                            titleFile.readText()
                        } else {
                            "Untitled Notice"
                        }
                        val date = Date(file.lastModified())
                        val dateFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
                        val timestamp = dateFormat.format(date)
                        val filename = file.nameWithoutExtension // e.g., "notice_1713987256179"


                        notices.add(
                            Notice(
                                title = title,
                                imageBitmap = bitmap,
                                timestamp = timestamp,
                                filename = filename
                            )
                        )
                    } else {
                        Log.w("ViewNoticeActivity", "Could not decode bitmap for file: ${file.name}")
                    }
                }


                noticeRecyclerView.adapter?.notifyDataSetChanged()
            } else {
                Log.w("ViewNoticeActivity", "Notice directory does not exist or is not a directory.")
            }
        } catch (e: Exception) {
            Log.e("ViewNoticeActivity", "Error loading notices: ${e.message}", e)
        }
    }
    private fun showDeleteConfirmation(notice: Notice, position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Delete Notice")
            .setMessage("Are you sure you want to delete this notice?")
            .setPositiveButton("Delete") { _, _ ->
                val viewHolder = noticeRecyclerView.findViewHolderForAdapterPosition(position)
                viewHolder?.itemView?.let { itemView ->
                    val rotate_and_fade_out = AnimationUtils.loadAnimation(this, R.anim.rotate_and_fade_out)


                    itemView.startAnimation(rotate_and_fade_out)


                    itemView.postDelayed({
                        deleteNotice(notice, position)
                    }, 500)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }


    private fun deleteNotice(notice: Notice, position: Int) {
        val noticeDir = File(filesDir, "notices")
        val imageFile = File(noticeDir, "${notice.title}.jpg")
        val titleFile = File(noticeDir, "${notice.title}.txt")


        val removedNotice = notices[position]


        if (imageFile.exists()) imageFile.delete()
        if (titleFile.exists()) titleFile.delete()


        notices.removeAt(position)
        noticeRecyclerView.adapter?.notifyItemRemoved(position)


        Snackbar.make(noticeRecyclerView, "Notice deleted", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                notices.add(position, removedNotice)
                noticeRecyclerView.adapter?.notifyItemInserted(position)


                // Restore the deleted files
                val restoredImage = File(noticeDir, "${removedNotice.title}.jpg")
                val restoredText = File(noticeDir, "${removedNotice.title}.txt")
                restoredImage.writeBytes((removedNotice.imageBitmap).let {
                    val stream = java.io.ByteArrayOutputStream()
                    it.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    stream.toByteArray()
                })
                restoredText.writeText(removedNotice.title)
            }.show()
    }
}
