package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import com.example.coursework2.MovieEntity;
import com.example.coursework2.MovieDao;
import com.example.coursework2.MovieDatabase;

import java.util.ArrayList;
import java.util.List;

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

        movieDao = MovieDatabase.getInstance(this).movieDao();

        loadSavedMovie();

        ivBack.setOnClickListener(v -> {
            Intent intent = new Intent(SavedMovieActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void loadSavedMovie() {
        new Thread(() -> {
            List<MovieEntity> movies = movieDao.getAllMovies();

            if(movieTitles == null) {
                movieTitles = new ArrayList<>();
            } else {
                movieTitles.clear();
            }

            for(MovieEntity m : movies) {
                movieTitles.add(m.title + "(" + m.year + ")");
            }

            runOnUiThread(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                  SavedMovieActivity.this,
                        R.layout.list_item_movie,
                        R.id.tvMovieTitle,
                        movieTitles
                );
                listViewSaved.setAdapter(adapter);
            });
        });
    }


}