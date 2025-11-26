package com.example.coursework2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
 public interface MovieDao {

    @Insert void insert(MovieEntity movie);
    @Delete void delete(MovieEntity movie);
    @Query("SELECT * FROM MovieTable")
    List<MovieEntity> getAllMovies();

    @Query("SELECT * FROM MovieTable WHERE LOWER(strTitle) LIKE '%' ||  LOWER(:titlePart) || '%'")
    List<MovieEntity> searchByTitle(String titlePart);

    @Query("SELECT * FROM MovieTable WHERE LOWER(strActors) LIKE '%' || LOWER(:actorPart) || '%'")
    List<MovieEntity> searchByActor(String actorPart);

    @Query("SELECT * FROM MovieTable WHERE LOWER(strGenre) LIKE '%' || LOWER(:genrePart) || '%'")
   List<MovieEntity> searchByGenre(String genrePart);
}