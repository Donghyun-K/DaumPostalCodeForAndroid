package com.test.postalcode

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView


class DaumPostalCodeActivity: AppCompatActivity() {

    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_webview)

        webView = findViewById<View>(R.id.webview) as WebView
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.addJavascriptInterface(AndroidBridge(), "Test")
        webView.loadUrl("https://donghyun-k.github.io/DaumPostalCodeWeb/index.html")
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.clearCache(true)
    }

    private inner class AndroidBridge {

        @JavascriptInterface
        fun setAddress(zipCode: String, address: String, buildingName: String) {
            Handler().post {
                val intent = Intent()
                intent.putExtra("zipCode", zipCode)
                intent.putExtra("address", "$address $buildingName")
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}