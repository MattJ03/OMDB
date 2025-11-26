package com.example.coursework2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static final String DB_NAME = "movie_database";
    private static volatile MovieDatabase INSTANCE;
    public abstract MovieDao movieDao();

   public static MovieDatabase getInstance(final Context context) {
       if(INSTANCE == null) {
           synchronized (MovieDatabase.class) {
               if(INSTANCE == null) {
                   INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                           MovieDatabase.class, DB_NAME)
                           .fallbackToDestructiveMigration()
                           .addCallback(prepopulateCallback)
                           .build();
               }
           }
       }
       return INSTANCE;
   }

   private static final Callback prepopulateCallback = new Callback() {
   @Override
   public void onCreate(@NonNull SupportSQLiteDatabase db) {
       super.onCreate(db);

       //running it on a new thread to avoid blocking the main thread
       Executors.newSingleThreadExecutor().execute(() -> {
           MovieDao dao = INSTANCE.movieDao();

           MovieEntity lotr = new MovieEntity();
           lotr.title = "The Lord of the Rings: The Return of the King";
           lotr.year = 2003;
           lotr.rated = "PG-13";
           lotr.released = "17 Dec 2003";
           //I store runtime in int
           lotr.runtime = 201;
           lotr.genre = "Action, Adventure, Drama";
           lotr.director = "Peter Jackson";
           lotr.writer = "J.R.R Tolkien";
           lotr.actors = "Elijah Wood, Viggo Mortensen, Ian McKellen";
           lotr.plot = "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.";

           MovieEntity inception = new MovieEntity();
           inception.title = "Inception";
           inception.year = 2010;
           inception.rated = "PG-13";
           inception.released = "16 Jul 2010";
           inception.runtime = 148;
           inception.genre = "Action, Adventure, Sci-Fi";
           inception.director = "Christopher Nolan";
           inception.writer = "Christopher Nolan";
           inception.actors = "Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page";
           inception.plot = "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.";

            dao.insert(lotr);
            dao.insert(inception);
       });
   }
   };


}
