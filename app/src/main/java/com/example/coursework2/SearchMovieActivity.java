package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

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
    private TextView tvMovieDetails;

    private MovieDao movieDao;
    private static final String API_KEY = "b00d827b";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        etMovieTitle = (EditText) findViewById(R.id.etMovieTitle);
        tvMovieDetails = (TextView) findViewById(R.id.tvMovieDetails);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);

        movieDao = MovieDatabase.getInstance(this).movieDao();

        btnSearch.setOnClickListener(v -> searchMovie());

    }

    private void searchMovie() {
        String title = etMovieTitle.getText().toString().trim();
        if(title.isEmpty()) {
            etMovieTitle.setError("Please enter a movie title");
            Toast.makeText(SearchMovieActivity.this, "Title cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        String url = "https://www.omdbapi.com/?t=" + title + "&apikey=" + API_KEY;

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> handleApiResponse(response),
                error -> tvMovieDetails.setText("Error :" + error.toString())
        );
        queue.add(request);
    }

    private void handleApiResponse(JSONObject response) {
        try {
            if(response.getString("Response").equals("False")) {
                tvMovieDetails.setText("Movie Not Found.");
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

            String movieDetails =
                    "Title: " + title + "\n" +
                    "Year: " + year + "\n" +
                    "Rated: " + rated + "\n" +
                    "Released: " + released + "\n" +
                    "Runtime: " + runtime + "\n" +
                    "Genre: " + genre + "\n" +
                    "Director: " + director + "\n" +
                    "Writer: " + writer + "\n" +
                    "Actors: " + actors + "\n\n" +
                    "Plot:\n " + plot;

            tvMovieDetails.setText(movieDetails);
        } catch(JSONException e) {
            tvMovieDetails.setText("JSON Error: " + e.getMessage());
        }
    }

}