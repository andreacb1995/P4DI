package com.example.andreacarballidop4di.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.example.andreacarballidop4di.R;
import com.example.andreacarballidop4di.core.Planta;
import com.example.andreacarballidop4di.database.PlantaLab;
import com.google.android.material.textview.MaterialTextView;

public class InformacionPlanta extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_planta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView  imageView = findViewById(R.id.imageView);
        MaterialTextView nombreCientifico = findViewById(R.id.nombreCientificoPlanta);
        WebView webview = findViewById(R.id.webview);

        PlantaLab myLab = PlantaLab.get(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Planta planta = myLab.getPlanta(id);

       imageView.setImageResource(planta.getImagen());
        this.setTitle(planta.getNombre());
         nombreCientifico.setText(planta.getNombreCientifico());
        webview.getSettings().setJavaScriptEnabled(true);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().toString().contains("wikipedia")) return false;

                Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
                startActivity(intent);
                return true;
            }
        });
        webview.loadUrl(planta.getUrl());


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
