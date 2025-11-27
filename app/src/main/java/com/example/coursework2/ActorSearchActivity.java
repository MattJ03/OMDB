package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ActorSearchActivity extends AppCompatActivity {

    private EditText etActorName;
    private TextView tvActorResults;
    private MovieDao movieDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_search);

        etActorName = findViewById(R.id.etActorName);
        tvActorResults = findViewById(R.id.tvActorResults);
        Button btnSearch = findViewById(R.id.btnSearchActor);

        movieDao = MovieDatabase.getInstance(this).movieDao();

        btnSearch.setOnClickListener(v -> searchByActor());
    }

    private void searchByActor() {
        String query = etActorName.getText().toString().trim();

        if (query.isEmpty()) {
            etActorName.setError("Please enter an actor name");
            return;
        }

        List<MovieEntity> results = movieDao.searchByActor(query);

        if (results.isEmpty()) {
            tvActorResults.setText("No movies found for: " + query);
            return;
        }

        StringBuilder builder = new StringBuilder();

        for (MovieEntity movie : results) {
            builder.append("Title: ").append(movie.title).append("\n")
                    .append("Year: ").append(movie.year).append("\n")
                    .append("Actors: ").append(movie.actors).append("\n")
                    .append("Plot: ").append(movie.plot).append("\n\n");
        }

        tvActorResults.setText(builder.toString());
    }
}
