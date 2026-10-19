package com.hpavan19.bajanasongs;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity {
    private static final int VOICE_REQUEST = 4102;
    private WebView webView;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        // The website opens inside the app. First successful load requires internet.
        webView.loadUrl("https://hpavan19-bajana-songs.static.hf.space/");
    }

    // Native Kannada voice-search can be launched from the app menu in a later integration.
    private void startKannadaVoiceSearch() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "kn-IN");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "ಹಾಡಿನ ಹೆಸರನ್ನು ಹೇಳಿ");
        try {
            startActivityForResult(intent, VOICE_REQUEST);
        } catch (Exception e) {
            Toast.makeText(this, "Voice search is not available on this device", Toast.LENGTH_LONG).show();
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_REQUEST && resultCode == RESULT_OK && data != null) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (results != null && !results.isEmpty()) {
                String spoken = results.get(0).replace("\\", "\\\\").replace("'", "\\'");
                webView.evaluateJavascript(
                    "if(document.querySelector('input[type=search],input[placeholder*=Search],input')){" +
                    "document.querySelector('input[type=search],input[placeholder*=Search],input').value='" + spoken + "';" +
                    "document.querySelector('input[type=search],input[placeholder*=Search],input').dispatchEvent(new Event('input',{bubbles:true}));}", null);
            }
        }
    }

    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
}
