package com.deepseek.dshmobile.ui

import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.deepseek.dshmobile.R
import com.deepseek.dshmobile.databinding.ActivityMainBinding
import com.deepseek.dshmobile.service.DshEngineManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var engineReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWebView()
        checkAndStartEngine()
    }

    private fun setupWebView() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.allowFileAccess = true
        binding.webView.settings.databaseEnabled = true
        binding.webView.settings.setSupportZoom(true)
        binding.webView.settings.builtInZoomControls = true
        binding.webView.settings.displayZoomControls = false
        binding.webView.settings.useWideViewPort = true
        binding.webView.settings.loadWithOverviewMode = true

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
                engineReady = true
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                if (request?.isForMainFrame == true) {
                    binding.progressBar.visibility = View.GONE
                    showErrorState("页面加载失败: ${error?.description}")
                }
            }
        }

        binding.webView.settings.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
    }

    private fun checkAndStartEngine() {
        if (DshEngineManager.isRunning) {
            loadHarnessUI()
        } else {
            showLoadingState()
            startEngine()
        }
    }

    private fun startEngine() {
        Toast.makeText(this, getString(R.string.connecting), Toast.LENGTH_SHORT).show()
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun loadHarnessUI() {
        val baseUrl = "http://127.0.0.1:3080"
        binding.webView.loadUrl(baseUrl)
    }

    private fun showLoadingState() {
        binding.progressBar.visibility = View.VISIBLE
        binding.emptyStateView.visibility = View.GONE
    }

    private fun showErrorState(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.emptyStateView.visibility = View.VISIBLE
        binding.errorTextView.text = message
    }

    override fun onResume() {
        super.onResume()
        if (engineReady && binding.webView.url?.startsWith("http") == true) {
            binding.webView.reload()
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.webView.destroy()
    }
}
