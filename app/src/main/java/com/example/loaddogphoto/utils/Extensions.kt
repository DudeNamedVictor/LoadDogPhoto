package com.example.loaddogphoto.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun saveImageIntoDownloads(imageName: String) {
    val fileName =
        "dog" + SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(
            Date()
        ) + ".jpg"
    val file = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .toString(), fileName
    )
    val bitmap = BitmapFactory.decodeStream(URL(imageName).openConnection().getInputStream())
    try {
        val out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        out.flush()
        out.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}