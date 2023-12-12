package com.example.familytree

import android.net.Uri
import android.os.Bundle
import android.widget.Button as AndroidButton // Rename the Android Button to avoid conflict
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.FileProvider
import com.example.familytree.ui.theme.FamilyTreeTheme
import com.google.firebase.inappmessaging.model.Button as FirebaseButton // Rename the Firebase Button to avoid conflict
import java.io.File

class MainActivity : ComponentActivity() {
    lateinit var imgView: ImageView
    lateinit var btnChange: AndroidButton // Use the AndroidButton alias
    lateinit var imageUri: Uri

    private var contract =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
            if (success) {
                imgView.setImageURI(imageUri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgView = findViewById(R.id.imgView)
        btnChange = findViewById(R.id.btnChange)
        imageUri = createImageUri()!!

        btnChange.setOnClickListener {
            contract.launch(imageUri)
            Toast.makeText(this,"Clicked", Toast.LENGTH_LONG ).show()
            changetext()
        }
    }

    private fun changetext() {
        val text1 : TextView = findViewById(R.id.text1)
        text1.text = "YOU LOOK GORJUSSSSSS!!!"
    }

    private fun createImageUri(): Uri? {
        val image = File(applicationContext.filesDir, "camera_photo.png")
        return FileProvider.getUriForFile(
            applicationContext,
            "com.android.Familytree.camerademo.FileProvider",
            image
        )
    }
}
