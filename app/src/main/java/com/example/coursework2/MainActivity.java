package com.example.coursework2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Intent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button mvSearch, acSearch, advSearch, myMovies;
    ImageView infoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mvSearch = findViewById(R.id.btnMovieSearch);
        acSearch = findViewById(R.id.btnActorSearch);
        advSearch = findViewById(R.id.btnAdvancedSearch);
        myMovies = findViewById(R.id.btnMyMovies);
        infoBtn = findViewById(R.id.ivInfo);

        mvSearch.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchMovieActivity.class);
            startActivity(intent);
        });

        acSearch.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActorSearchActivity.class);
            startActivity(intent);
        });

        advSearch.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdvancedSearchActivity.class);
            startActivity(intent);
        });

        myMovies.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SavedMovieActivity.class);
            startActivity(intent);
        });

        infoBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }

    protected boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    protected void showDialogForInternet() {
        AlertDialog dialog = new AlertDialog.Builder(
                .setTitle("No Internet Connection")
                .setMessage("Turn on the Internet")
                .setCancelable(false);
        );
    }
}
