package com.example.unit1projects.unit6

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.unit1projects.R

class MainActivity : AppCompatActivity() {
    private lateinit var googleWebView: WebView
    private lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main7)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        googleWebView = findViewById(R.id.googleWebView)

        googleWebView.settings.javaScriptEnabled = true
        googleWebView.settings.domStorageEnabled = true

        googleWebView.webViewClient = WebViewClient()
        googleWebView.webChromeClient = WebChromeClient()

        googleWebView.loadUrl("https://learnflow-pjd3.onrender.com")

        refreshLayout = findViewById(R.id.main)
        refreshLayout.setOnRefreshListener {
            googleWebView.reload()
        }
    }

    override fun onBackPressed() {
        if (googleWebView.canGoBack()) {
            googleWebView.goBack()
        }
        else {
            super.onBackPressed()
        }
    }
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun hideSystemUI() {
        window.setDecorFitsSystemWindows(false)
        window.insetsController?.let {
            it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            it.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

}