package com.example.coursework2;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MovieDaoInstrumentedTest {

    private MovieDatabase db;
    private MovieDao dao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase.class)
                .allowMainThreadQueries() // for testing purposes
                .build();
        dao = db.movieDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertAndRetrieveMovie() {
        MovieEntity movie = new MovieEntity();
        movie.title = "Test Movie";
        movie.year = 2025;
        movie.rated = "PG";
        movie.released = "01 Jan 2025";
        movie.runtime = 120;
        movie.genre = "Action";
        movie.director = "Jane Doe";
        movie.writer = "John Smith";
        movie.actors = "Actor A, Actor B";
        movie.plot = "This is a test plot.";

        dao.insert(movie);

        List<MovieEntity> movies = dao.getAllMovies();
        assertEquals(1, movies.size());
        assertEquals("Test Movie", movies.get(0).title);
    }

    @Test
    public void searchByActor() {
        MovieEntity movie = new MovieEntity();
        movie.title = "Test Movie";
        movie.year = 2025;
        movie.actors = "Actor A, Actor B";

        dao.insert(movie);

        List<MovieEntity> results = dao.searchByActor("actor a"); // case-insensitive
        assertFalse(results.isEmpty());
        assertEquals("Test Movie", results.get(0).title);
    }
}
