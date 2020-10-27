package com.example.myapplication.myapplication29;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView maWebView = null;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maWebView = findViewById(R.id.maWebView);
        maWebView.getSettings().setJavaScriptEnabled(true);
        maWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                maWebView.loadUrl(request.getUrl().toString());
                return true;
            }
        });
        String CONTENT = "<input type=\"button\" value=\"Test\" "+
                "onClick=\"showAndroidToast('coucou')\" />"+
                "<script>"+
                "function showAndroidToast(text)" +
                "{" +
                "Android.showToastPersoXXX(text);" +
                "}"+
                "</script>";

        maWebView.addJavascriptInterface(new JavascriptPerso(this), "Android");
        maWebView.loadData(CONTENT, "text/html", "UTF-8");
    }

    public class JavascriptPerso{
        Context context;
        public JavascriptPerso(Context context){
            this.context = context;
        }
        @JavascriptInterface
        public void showToastPersoXXX(String saisie){
            Toast.makeText(context, "Hello World", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            maWebView.goBack();
        }
        return super.onKeyDown(keyCode, event);
    }
}