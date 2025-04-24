package com.example.campussyncigdtuw

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button


class ChooseActionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_action_tt)


        val className = intent.getStringExtra("className")
        val day = intent.getStringExtra("day")


        // "Upload Timetable" Button
        findViewById<Button>(R.id.button_upload_timetable).setOnClickListener {
            val intent = Intent(this, ScheduleAdapter::class.java)
            intent.putExtra("className", className)
            intent.putExtra("day", day)
            intent.putExtra("action", "upload")
            startActivity(intent)
        }


        // "View Timetable" Button
        findViewById<Button>(R.id.button_view_timetable).setOnClickListener {
            val intent = Intent(this, ScheduleAdapter::class.java)
            intent.putExtra("className", className)
            intent.putExtra("day", day)
            intent.putExtra("action", "view")
            startActivity(intent)
        }
    }
}
