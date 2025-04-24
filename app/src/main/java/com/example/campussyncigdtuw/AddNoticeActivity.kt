package com.example.campussyncigdtuw

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.File
import java.io.FileOutputStream


class AddNoticeActivity : AppCompatActivity() {


    private lateinit var addNoticeCard: CardView
    private lateinit var noticeImageView: ImageView
    private lateinit var uploadButton: Button
    private lateinit var noticeTitle: EditText
    private lateinit var backButton: ImageButton
    private lateinit var previewImageView: ImageView


    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notice)


        // Initialize views
        addNoticeCard = findViewById(R.id.addNotice)
        noticeImageView = findViewById(R.id.noticeImageView)
        uploadButton = findViewById(R.id.uploadnoticeBtn)
        noticeTitle = findViewById(R.id.noticeTitle)
        backButton = findViewById(R.id.backButton)
        previewImageView = findViewById(R.id.previewImageView)


        // Setup Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        // Back button with transition
        backButton.setOnClickListener {
            val intent = Intent(this, NoticeBoardActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            finish()
        }


        // Image selection
        addNoticeCard.setOnClickListener {
            openImageChooser()
        }


        // Upload logic (save image + title)
        uploadButton.setOnClickListener {
            val title = noticeTitle.text.toString().trim()


            if (title.isEmpty() || imageUri == null) {
                Toast.makeText(this, "Please fill all fields and select an image", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    // Create unique filename
                    val timestamp = System.currentTimeMillis().toString()
                    val filename = "notice_$timestamp"


                    // Decode image
                    val inputStream = contentResolver.openInputStream(imageUri!!)
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    inputStream?.close()


                    // Save image
                    val noticeDir = File(filesDir, "notices")
                    if (!noticeDir.exists()) noticeDir.mkdirs()


                    val imageFile = File(noticeDir, "$filename.jpg")
                    val outputStream = FileOutputStream(imageFile)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream.flush()
                    outputStream.close()


                    // Save title
                    val titleFile = File(noticeDir, "$filename.txt")
                    titleFile.writeText(title)


                    Toast.makeText(this, "Notice saved internally", Toast.LENGTH_SHORT).show()


                    // Reset UI
                    previewImageView.setImageDrawable(null)
                    previewImageView.visibility = ImageView.GONE
                    noticeTitle.text.clear()


                    Log.d("AddNoticeActivity", "Saved: ${imageFile.name} and ${titleFile.name}")


                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed to save notice", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data
            if (imageUri != null) {
                previewImageView.setImageURI(imageUri)
                previewImageView.visibility = ImageView.VISIBLE
            }
        }
    }
}
