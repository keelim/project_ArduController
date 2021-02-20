package com.keelim.arducon.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.keelim.arducon.R
import com.keelim.arducon.databinding.ActivityWebBinding


class WebViewActivity : AppCompatActivity(R.layout.activity_web) {
    private lateinit var binding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)

        binding.webview.run {
            loadUrl(getString(R.string.bugurl))
            webViewClient = WebViewClient() // 클릭시 새창이 뜨지 않는다.?
            webChromeClient = WebChromeClient() //웹뷰에 크롬 사용 허용//이 부분이 없으면 크롬에서 알림 뜨지 않음
            scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
            isScrollbarFadingEnabled = true
            setLayerType(View.LAYER_TYPE_HARDWARE, null)
        }

        binding.webview.settings.run {
            loadWithOverviewMode = true
            useWideViewPort = true
            setSupportZoom(true)
            builtInZoomControls = false
            layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
            cacheMode = WebSettings.LOAD_NO_CACHE
            domStorageEnabled = true
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.webview.canGoBack()) {
            binding.webview.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}