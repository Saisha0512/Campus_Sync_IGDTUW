package com.example.campussyncigdtuw

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore


class UploadTimeTableTTActivity : AppCompatActivity() {


    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upload_time_table_tt)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val periodField = findViewById<EditText>(R.id.edit_period)
        val facultyField = findViewById<EditText>(R.id.edit_faculty)
        val topicField = findViewById<EditText>(R.id.edit_topic)
        val saveButton = findViewById<Button>(R.id.button_save)


        val className = intent.getStringExtra("className")
        val day = intent.getStringExtra("day")


        saveButton.setOnClickListener {
            val period = periodField.text.toString()
            val faculty = facultyField.text.toString()
            val topic = topicField.text.toString()


            if (period.isNotEmpty() && faculty.isNotEmpty() && topic.isNotEmpty() && className != null && day != null) {
                val timetableData = hashMapOf(
                    "Faculty" to faculty,
                    "Topic" to topic
                )


                db.collection("Classes").document(className)
                    .collection(day)
                    .document(period)
                    .set(timetableData)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Timetable updated successfully!", Toast.LENGTH_SHORT).show()


                        val resultIntent = Intent()
                        resultIntent.putExtra("period", period)
                        resultIntent.putExtra("faculty", faculty)
                        resultIntent.putExtra("topic", topic)
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
