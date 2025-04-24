package com.example.campussyncigdtuw


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp


class TimeTableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table)


        // Initialize Firebase
        FirebaseApp.initializeApp(this)


        // Buttons for class selection
        findViewById<Button>(R.id.button_cse1).setOnClickListener { navigateToDaySelection("CSE 1") }
        findViewById<Button>(R.id.button_cse2).setOnClickListener { navigateToDaySelection("CSE 2") }
        findViewById<Button>(R.id.button_cse3).setOnClickListener { navigateToDaySelection("CSE 3") }
        findViewById<Button>(R.id.button_cse_ai1).setOnClickListener { navigateToDaySelection("CSE AI 1") }
        findViewById<Button>(R.id.button_cse_ai2).setOnClickListener { navigateToDaySelection("CSE AI 2") }
        findViewById<Button>(R.id.button_cse_ai3).setOnClickListener { navigateToDaySelection("CSE AI 3") }
        findViewById<Button>(R.id.button_ai_ml).setOnClickListener { navigateToDaySelection("AI-ML") }
        findViewById<Button>(R.id.button_ece1).setOnClickListener { navigateToDaySelection("ECE 1") }
        findViewById<Button>(R.id.button_ece2).setOnClickListener { navigateToDaySelection("ECE 2") }
    }


    private fun navigateToDaySelection(className: String) {
        val intent = Intent(this, SelectDayActivity::class.java)
        intent.putExtra("className", className)
        startActivity(intent)
    }
}
