package com.floridevs.dashithing;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    WebView myWeb;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Aktiviert das Edge-to-Edge-Design (Vollbild)
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // WebView mit der XML-ID verknüpfen
        myWeb = findViewById(R.id.myWeb);

        // ==========================================
        // WEBVIEW-EINSTELLUNGEN & JAVASCRIPT-FIXES
        // ==========================================

        // 1. JavaScript aktivieren
        myWeb.getSettings().setJavaScriptEnabled(true);

        // 2. WICHTIG: Erlaube den Zugriff auf lokale Datei-URLs (behebt CORS/Blockaden bei Assets)
        myWeb.getSettings().setAllowFileAccessFromFileURLs(true);
        myWeb.getSettings().setAllowUniversalAccessFromFileURLs(true);
        myWeb.getSettings().setAllowFileAccess(true);

        // 3. WICHTIG: Speicher für moderne Skripte aktivieren (falls dein JS localStorage nutzt)
        myWeb.getSettings().setDomStorageEnabled(true);

        // 4. Clients setzen (WebChromeClient ist wichtig, damit z.B. alert() oder console.log() im JS gehen)
        myWeb.setWebViewClient(new WebViewClient());
        myWeb.setWebChromeClient(new WebChromeClient());

        // 5. Lokale Datei aus dem Assets-Ordner laden
        myWeb.loadUrl("file:///android_asset/index.html");

        // ==========================================

        // Systemleisten-Padding (Status- und Navigationsleiste)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}