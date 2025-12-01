package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdvancedSearchActivity extends AppCompatActivity {

    private EditText etSearchTitle;
    private ListView listViewResults;
    private ImageView imgPoster, imgBack;
    private final ArrayList<String> movieTitles = new ArrayList<>();
    private final ArrayList<String> imdbIds = new ArrayList<>();

    private static final String API_KEY = ApiConfig.API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        etSearchTitle = (EditText) findViewById(R.id.etSearchTitle);
        listViewResults = (ListView) findViewById(R.id.listViewResults);
        imgBack = (ImageView) findViewById(R.id.imgAdvBack);
        imgPoster = (ImageView) findViewById(R.id.imgPoster);

        Button btnSearch = findViewById(R.id.btnAdvancedSearch);
        btnSearch.setOnClickListener(v -> searchMovies());

        listViewResults.setOnItemClickListener((parent, view, position, id) -> {
            String imdbID = imdbIds.get(position);
            loadPoster(imdbID);
        });

        imgBack.setOnClickListener(v -> {
            Intent intent = new Intent(AdvancedSearchActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void searchMovies() {
        String query = etSearchTitle.getText().toString().trim();

        if (query.isEmpty()) {
            etSearchTitle.setError("Enter a movie title");
            return;
        } else if(query.length() > 65) {
            etSearchTitle.setError("Input too long");
            Toast.makeText(AdvancedSearchActivity.this, "Entered search query is too long", Toast.LENGTH_LONG).show();
        }

        String url = "https://www.omdbapi.com/?s=" + query + "&apikey=" + API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                this::handleSearchResponse,
                error -> Toast.makeText(this, "API error", Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(this).add(request);
    }

    private void handleSearchResponse(JSONObject response) {
        try {
            if (!response.getString("Response").equals("True")) {
                Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
                return;
            }

            JSONArray results = response.getJSONArray("Search");

            movieTitles.clear();
            imdbIds.clear();

            for (int i = 0; i < results.length(); i++) {
                JSONObject obj = results.getJSONObject(i);
                movieTitles.add(obj.getString("Title"));
                imdbIds.add(obj.getString("imdbID"));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    AdvancedSearchActivity.this,
                    R.layout.list_item_movie,
                    R.id.tvMovieTitle,
                    movieTitles
            );

            listViewResults.setAdapter(adapter);

        } catch (Exception e) {
            Toast.makeText(this, "Parsing error", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadPoster(String imdbID) {
        String url = "https://www.omdbapi.com/?i=" + imdbID + "&apikey=" + API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        String posterUrl = response.getString("Poster");
                        Picasso.get().load(posterUrl).into(imgPoster);
                    } catch (Exception ignored) {}
                },
                error -> Toast.makeText(this, "Poster load error", Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(this).add(request);
    }
}