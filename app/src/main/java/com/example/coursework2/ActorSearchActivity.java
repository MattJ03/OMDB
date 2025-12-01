package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActorSearchActivity extends AppCompatActivity {

    private EditText etActorName;
    private ListView lvActorResults;
    private ImageView imgBack;
    private MovieDao movieDao;
    private ArrayList<String> actorMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_search);

        etActorName = findViewById(R.id.etActorName);
        lvActorResults = findViewById(R.id.lvActorResults);
        imgBack = findViewById(R.id.imgActorBack);
        Button btnSearch = findViewById(R.id.btnSearchActor);

        movieDao = MovieDatabase.getInstance(this).movieDao();

        btnSearch.setOnClickListener(v -> searchByActor());

        imgBack.setOnClickListener(v -> finish()); // go back to previous activity
    }

    private void searchByActor() {
        String query = etActorName.getText().toString().trim();
        if (query.isEmpty()) {
            etActorName.setError("Please enter an actor name");
            return;
        } else if(query.length() > 65) {
            etActorName.setError("Input too long");
            Toast.makeText(ActorSearchActivity.this, "Entered search query is too long", Toast.LENGTH_LONG).show();
        }

        new Thread(() -> {
            List<MovieEntity> results = movieDao.searchByActor(query);

            if (actorMovies == null) {
                actorMovies = new ArrayList<>();
            } else {
                actorMovies.clear();
            }

            for (MovieEntity movie : results) {
                actorMovies.add(movie.title + " (" + movie.year + ")");
            }

            runOnUiThread(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        ActorSearchActivity.this,
                        R.layout.list_item_movie, // same as SavedMovieActivity
                        R.id.tvMovieTitle,
                        actorMovies
                );
                lvActorResults.setAdapter(adapter);
            });
        }).start();
    }
}
