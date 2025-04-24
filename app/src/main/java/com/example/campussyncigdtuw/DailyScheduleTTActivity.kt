package com.example.campussyncigdtuw

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class DailyScheduleTTActivity : AppCompatActivity() {


    private val db = FirebaseFirestore.getInstance()
    private lateinit var timetableAdapter: TimetableAdapter
    private lateinit var className: String
    private lateinit var day: String


    private val uploadActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val period = data?.getStringExtra("period")
            val faculty = data?.getStringExtra("faculty")
            val topic = data?.getStringExtra("topic")


            if (period != null && faculty != null && topic != null) {
                timetableAdapter.addUpdatedData(period, faculty, topic)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_schedule_tt)


        className = intent.getStringExtra("className") ?: "Unknown"
        day = intent.getStringExtra("day") ?: "Unknown"


        val recyclerView = findViewById<RecyclerView>(R.id.timetable_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        timetableAdapter = TimetableAdapter(mutableListOf())
        recyclerView.adapter = timetableAdapter


        fetchTimetableFromFirestore()


        val updateButton = findViewById<Button>(R.id.button_update_timetable)
        updateButton.setOnClickListener {
            val intent = Intent(this, UploadTimeTableTTActivity::class.java)
            intent.putExtra("className", className)
            intent.putExtra("day", day)
            uploadActivityLauncher.launch(intent)
        }
    }


    private fun fetchTimetableFromFirestore() {
        db.collection("Classes").document(className)
            .collection(day)
            .get()
            .addOnSuccessListener { documents ->
                val scheduleItems = mutableListOf<ScheduleItem>()
                for (document in documents) {
                    val period = document.id
                    val faculty = document.getString("Faculty") ?: ""
                    val topic = document.getString("Topic") ?: ""
                    scheduleItems.add(ScheduleItem(period, faculty, topic))
                }
                timetableAdapter.updateData(scheduleItems)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
