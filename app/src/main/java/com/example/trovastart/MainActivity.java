package com.example.trovastart;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trovastart.ui.main.SectionsPagerAdapter;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView text_title_trova, text_comunicate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        //inicializaciones
        text_title_trova = (TextView)findViewById(R.id.title_trova);
        text_comunicate = (TextView) findViewById(R.id.comunicate);


        //USO DE FUENTE MONSTER
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/mrsmonster.ttf");
        text_title_trova.setTypeface(face);

        //permisos
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        }

        text_comunicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent enviaIntent = new Intent (Intent.ACTION_SEND);
                    enviaIntent.setType("text/plain");
                    enviaIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "trova.co@gmail.com" });
                    enviaIntent.putExtra(Intent.EXTRA_SUBJECT, "trova.co@gmail.com");
                    startActivity(enviaIntent);
            }
        });




    }
}