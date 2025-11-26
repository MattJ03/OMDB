package com.example.coursework2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MovieTable")
public class MovieEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "strTitle")
    public String title;

    @ColumnInfo(name = "intYear")
    public int year;

    @ColumnInfo(name = "strRated")
    public String rated;

    @ColumnInfo(name = "strReleased")
    public String released;

    @ColumnInfo(name = "intRuntime")
    public int runtime;

    @ColumnInfo(name = "strGenre")
    public String genre;

    @ColumnInfo(name = "strDirector")
    public String director;

    @ColumnInfo(name = "strWriter")
    public String writer;

    @ColumnInfo(name = "strActors")
    public String actors;

    @ColumnInfo(name = "strPlot")
    public String plot;

}