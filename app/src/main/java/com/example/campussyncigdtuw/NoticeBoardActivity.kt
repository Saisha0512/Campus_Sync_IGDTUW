package com.example.campussyncigdtuw

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import android.widget.ImageButton
import android.widget.ImageView


class NoticeBoardActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_board)

        // Setup Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)  // Disable default "Mad..."

        val backButton: ImageButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            finish()
        }

        // Card: Upload Notice
        val addNoticeCard: CardView = findViewById(R.id.addNotice)
        addNoticeCard.setOnClickListener {
            val intent = Intent(this, AddNoticeActivity::class.java)
            startActivity(intent)
        }

        // Card: View Notice
        val addImageCard: CardView = findViewById(R.id.viewNotice)
        addImageCard.setOnClickListener {
            Log.d("ViewNoticeDebug", "Launching ViewNoticeActivity")
            val intent = Intent(this, ViewNoticeActivity::class.java)
            startActivity(intent)
        }

        // Logo (optional)
        val logoImageView: ImageView = findViewById(R.id.campus_sync_logo)
        logoImageView.setImageResource(R.drawable.canpus_sync_logo)
    }
}
