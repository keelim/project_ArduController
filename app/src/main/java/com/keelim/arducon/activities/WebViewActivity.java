package com.keelim.arducon.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.keelim.arducon.R;
import com.keelim.arducon.databinding.ActivityWebViewBinding;

public class WebViewActivity extends AppCompatActivity { //버그 리포트로 이동을 하는 액티비티
    private ActivityWebViewBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        binding.setActivity(this);
        binding.webView.loadUrl(getString(R.string.bugurl));
        binding.webView.setWebViewClient(new WebViewClient()); // 클릭시 새창이 뜨지 않는다.?
        binding.webView.setWebChromeClient(new WebChromeClient());//웹뷰에 크롬 사용 허용//이 부분이 없으면 크롬에서 알림 뜨지 않음
        binding.webView.setForceDarkAllowed(true);
        binding.webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        binding.webView.setScrollbarFadingEnabled(true);
        binding.webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        WebSettings settings = binding.webView.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && binding.webView.canGoBack()) {
            binding.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
