package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.coursework2.MovieDatabase;
import com.example.coursework2.MovieDao;
import com.example.coursework2.MovieEntity;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchMovieActivity extends AppCompatActivity {

    private EditText etMovieTitle;
    private TextView tvTitle, tvYear, tvRated, tvReleased, tvRuntime, tvGenre, tvDirector, tvWriter, tvActors, tvPlot;

    private MovieDao movieDao;
    private static final String API_KEY = ApiConfig.API_KEY;

    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        etMovieTitle = (EditText) findViewById(R.id.etMovieTitle);
        tvTitle = findViewById(R.id.tvTitle);
        tvYear = findViewById(R.id.tvYear);
        tvRated = findViewById(R.id.tvRated);
        tvReleased = findViewById(R.id.tvReleased);
        tvRuntime = findViewById(R.id.tvRuntime);
        tvGenre = findViewById(R.id.tvGenre);
        tvDirector = findViewById(R.id.tvDirector);
        tvWriter = findViewById(R.id.tvWriter);
        tvActors = findViewById(R.id.tvActors);
        tvPlot = findViewById(R.id.tvPlot);
        imgBack = findViewById(R.id.imgBack);

        Button btnSearch = (Button) findViewById(R.id.btnSearch);

        movieDao = MovieDatabase.getInstance(this).movieDao();

        btnSearch.setOnClickListener(v -> searchMovie());

        imgBack.setOnClickListener(v -> {
            Intent intent = new Intent(SearchMovieActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }

    private void searchMovie() {
        String title = etMovieTitle.getText().toString().trim();
        if(title.isEmpty()) {
            etMovieTitle.setError("Please enter a movie title");
            Toast.makeText(SearchMovieActivity.this, "Title cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        String url = "https://www.omdbapi.com/?t=" + title.replace(" ", "+") + "&apikey=" + API_KEY;

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> handleApiResponse(response),
                error -> tvTitle.setText("Error :" + error.toString())
        );
        queue.add(request);
    }

    private void handleApiResponse(JSONObject response) {
        try {
            if(response.getString("Response").equals("False")) {
                tvTitle.setText("Movie Not Found.");
                return;
            }
            String title = response.getString("Title");
            String year = response.getString("Year");
            String rated = response.getString("Rated");
            String released = response.getString("Released");
            String runtime = response.getString("Runtime");
            String genre = response.getString("Genre");
            String director = response.getString("Director");
            String writer = response.getString("Writer");
            String actors = response.getString("Actors");
            String plot = response.getString("Plot");

            tvTitle.setText(title);
            tvYear.setText("Year: " + year);
            tvRated.setText("Rated " + rated);
            tvReleased.setText("Released: " + released);
            tvRuntime.setText("Runtime: " + runtime);
            tvGenre.setText("Genre: " + genre);
            tvDirector.setText("Director: " + director);
            tvWriter.setText("Writer: " + writer);
            tvActors.setText("Actors: " + actors);
            tvPlot.setText("Plot: " + plot);

         MovieEntity movie = new MovieEntity();
         movie.title = title;
         movie.year = Integer.parseInt(year);
         movie.rated = rated;
         movie.released = released;
         movie.runtime = Integer.parseInt(runtime.replace(" min", ""));
         movie.genre = genre;
         movie.director = director;
         movie.writer = writer;
         movie.actors = actors;
         movie.plot = plot;

         new Thread(() -> movieDao.insert(movie)).start();

        } catch(JSONException e) {
            tvTitle.setText("JSON Error: " + e.getMessage());
        }
    }

}