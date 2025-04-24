package com.example.campussyncigdtuw.model

import android.graphics.Bitmap

data class Notice(
    val title: String,
    val imageBitmap: Bitmap, // This must match what you're passing in
    val timestamp: String,
    val filename:String // <- Add this field to identify the file
)
