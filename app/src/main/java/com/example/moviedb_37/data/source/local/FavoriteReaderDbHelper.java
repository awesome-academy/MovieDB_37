package com.example.moviedb_37.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.moviedb_37.data.model.Movie;
import com.example.moviedb_37.data.source.local.contract.FavoriteReaderContract;

import java.util.ArrayList;
import java.util.List;

public class FavoriteReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MovieDB.db";

    private static final String LIKE_ARG = " LIKE ?";
    private static final String QUESTION_ARG = " = ?";
    private static final String OPEN_PARENTHESIS = " (";
    private static final String CLOSE_PARENTHESIS = ")";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA = ",";
    private static final String DROP_IF_EXIST = "DROP TABLE IF EXISTS ";

    private static final String SQL_CREATE_FAVORITE_ENTRY =
            "CREATE TABLE " + FavoriteReaderContract.FavoriteEntry.TABLE_NAME + OPEN_PARENTHESIS +
                    FavoriteReaderContract.FavoriteEntry._ID + INTEGER_TYPE + PRIMARY_KEY + COMMA +
                    FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID + TEXT_TYPE + COMMA +
                    FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA +
                    FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_BACK_DROP_PATH + TEXT_TYPE + COMMA +
                    FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_POSTER_PATH + TEXT_TYPE + COMMA +
                    FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_VOTE_AVERAGE + TEXT_TYPE +
                    CLOSE_PARENTHESIS;

    private static final String SQL_DELETE_FAVORITE_ENTRY =
            DROP_IF_EXIST + FavoriteReaderContract.FavoriteEntry.TABLE_NAME;

    public FavoriteReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_FAVORITE_ENTRY);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean putTrack(Movie movie) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID, movie.getId());
        values.put(FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_TITLE, movie.getTitle());
        values.put(FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_BACK_DROP_PATH,
                movie.getBackdropPath());
        values.put(FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_POSTER_PATH,
                movie.getPosterPath());
        values.put(FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_VOTE_AVERAGE,
                movie.getVoteAverage());

        long id = db.insert(FavoriteReaderContract.FavoriteEntry.TABLE_NAME,
                null, values);
        db.close();
        return id > 0 ? true : false;
    }

    public List<Movie> getMovie() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] projection = {
                FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID,
                FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_TITLE,
                FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_BACK_DROP_PATH,
                FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_POSTER_PATH,
                FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_VOTE_AVERAGE,
        };
        Cursor cursor = sqLiteDatabase.query(
                FavoriteReaderContract.FavoriteEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        ArrayList<Movie> movies = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID));
            String title = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_TITLE));
            String backDropPath = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_BACK_DROP_PATH));
            String posterPath = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_POSTER_PATH));
            String voteAverage = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_VOTE_AVERAGE));
            Movie movieObject = new Movie(Integer.valueOf(id), title, posterPath,
                    Float.valueOf(voteAverage));
            movieObject.setBackdropPath(backDropPath);
            movies.add(movieObject);
        }
        sqLiteDatabase.close();
        return movies;
    }

    public boolean deleteMovie(Movie movie) {
        long trackId = movie.getId();
        SQLiteDatabase db = getReadableDatabase();
        String selection = FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID + LIKE_ARG;
        String[] selectionArgs = {String.valueOf(trackId)};
        int deletedRows = db.delete(
                FavoriteReaderContract.FavoriteEntry.TABLE_NAME,
                selection,
                selectionArgs);
        db.close();
        return deletedRows > 0 ? true : false;
    }

    public boolean canAddMovie(Movie movie) {
        long trackId = movie.getId();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] projection = {
                FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID,
        };
        String selection = FavoriteReaderContract.FavoriteEntry.COLUMN_NAME_MOVIE_ID + QUESTION_ARG;
        String[] selectionArgs = {String.valueOf(trackId)};
        Cursor cursor = sqLiteDatabase.query(
                FavoriteReaderContract.FavoriteEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);
        sqLiteDatabase.close();
        return (cursor.getCount() <= 0);
    }
}
