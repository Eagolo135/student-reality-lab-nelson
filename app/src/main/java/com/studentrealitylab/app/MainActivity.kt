package com.studentrealitylab.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.studentrealitylab.app.ui.StudentRealityLabScreen
import com.studentrealitylab.app.ui.StudentRealityLabViewModel
import com.studentrealitylab.app.ui.theme.StudentRealityLabTheme

class MainActivity : ComponentActivity() {

    private val viewModel: StudentRealityLabViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentRealityLabTheme {
                StudentRealityLabScreen(viewModel = viewModel)
            }
        }
    }
}
