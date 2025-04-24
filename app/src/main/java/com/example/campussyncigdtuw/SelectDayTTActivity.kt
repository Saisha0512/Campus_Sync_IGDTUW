package com.example.campussyncigdtuw

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class SelectDayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_day_tt)


        val className = intent.getStringExtra("className")


        findViewById<Button>(R.id.button_monday).setOnClickListener { navigateToDailySchedule(className, "Monday") }
        findViewById<Button>(R.id.button_tuesday).setOnClickListener { navigateToDailySchedule(className, "Tuesday") }
        findViewById<Button>(R.id.button_wednesday).setOnClickListener { navigateToDailySchedule(className, "Wednesday") }
        findViewById<Button>(R.id.button_thursday).setOnClickListener { navigateToDailySchedule(className, "Thursday") }
        findViewById<Button>(R.id.button_friday).setOnClickListener { navigateToDailySchedule(className, "Friday") }
    }


    private fun navigateToDailySchedule(className: String?, day: String) {
        val intent = Intent(this, DailyScheduleTTActivity::class.java)
        intent.putExtra("className", className)
        intent.putExtra("day", day)
        startActivity(intent)
    }
}
