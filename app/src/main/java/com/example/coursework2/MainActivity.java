package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button mvSearch, acSearch, advSearch;
    ImageView infoBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mvSearch = (Button) findViewById(R.id.btnMovieSearch);
        acSearch = (Button) findViewById(R.id.btnActorSearch);
        advSearch = (Button) findViewById(R.id.btnAdvancedSearch);
        infoBtn = (ImageView) findViewById(R.id.ivInfo);

      mvSearch.setOnClickListener(v -> {
          Intent intent = new Intent(MainActivity.this, SearchMovieActivity.class);
          startActivity(intent);
      });

      acSearch.setOnClickListener(v -> {
          Intent intent = new Intent(MainActivity.this, ActorSearchActivity.class);
          startActivity(intent);
      });

    }
}