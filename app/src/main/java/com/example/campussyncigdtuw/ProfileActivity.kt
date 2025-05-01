package com.example.campussyncigdtuw

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var fullNameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("CampusSyncPrefs", Context.MODE_PRIVATE)

        // UI elements
        val backButton: ImageButton = findViewById(R.id.back_button)
        val updateButton: Button = findViewById(R.id.update_button)
        val usernameEditText: EditText = findViewById(R.id.username)
        val emailEditText: EditText = findViewById(R.id.email)
        val mobileEditText: EditText = findViewById(R.id.mobile_no)
        val passwordEditText: EditText = findViewById(R.id.password)
        fullNameTextView = findViewById(R.id.full_name)

        // Fetch and populate saved data from SharedPreferences
        val savedUsername = sharedPreferences.getString("username", "")
        val savedEmail = sharedPreferences.getString("email", "")
        val savedMobile = sharedPreferences.getString("mobile_no", "")
        val savedPassword = sharedPreferences.getString("password", "")

        usernameEditText.setText(savedUsername)
        emailEditText.setText(savedEmail)
        mobileEditText.setText(savedMobile)
        passwordEditText.setText(savedPassword)

        // Set the full name at the top
        fullNameTextView.text = "Name : $savedUsername"

        // Back button listener
        backButton.setOnClickListener {
            finish()  // Close the activity and return to the previous screen
        }

        // Update button listener
        updateButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val mobile = mobileEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Validate email format
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid Email Format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate if mandatory fields are filled
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || mobile.isEmpty()) {
                Toast.makeText(this, "All fields are mandatory!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save the updated information into SharedPreferences
            sharedPreferences.edit().apply {
                putString("username", username)
                putString("email", email)
                putString("mobile_no", mobile)
                putString("password", password)
                apply()
            }

            // Update the displayed full name
            fullNameTextView.text = "Name : $username"

            // Notify the user
            Toast.makeText(this, "Profile Updated Successfully!", Toast.LENGTH_SHORT).show()
        }

        // Logout button listener
        val logoutButton: Button = findViewById(R.id.logout_button)
        logoutButton.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()  // End current activity
        }
    }
}