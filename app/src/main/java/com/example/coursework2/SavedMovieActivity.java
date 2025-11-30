package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import com.example.coursework2.MovieEntity;
import com.example.coursework2.MovieDao;

import java.util.ArrayList;

public class SavedMovieActivity extends AppCompatActivity {

   private ListView listViewSaved;
   private ImageView ivBack;
   private ArrayList<String> movieTitles;
   private ArrayList<Integer> movieYear;
   private MovieDao movieDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_movie);

        listViewSaved = findViewById(R.id.listViewSaved);
        ivBack = findViewById(R.id.ivBack2);

    }

    protected void getSavedMovies() {

    }
}