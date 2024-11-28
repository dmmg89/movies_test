package com.conelab.moviesexam.data.database


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import com.conelab.moviesexam.data.model.MovieItem

class MovieDatabaseHelper(context: Context ) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "movies.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "movies"
        const val COLUMN_ID = "id"
        const val COLUMN_ORIGINAL_TITLE = "original_title"
        const val COLUMN_TITLE = "title"
        const val COLUMN_VOTE_AVERAGE = "vote_average"
        const val COLUMN_RELEASE_DATE = "release_date"
        const val COLUMN_BACKDROP_PATH = "backdrop_path"
        const val COLUMN_GENDER_IDS = "gender_ids"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY,
                $COLUMN_ORIGINAL_TITLE TEXT,
                $COLUMN_TITLE TEXT,
                $COLUMN_VOTE_AVERAGE REAL,
                $COLUMN_RELEASE_DATE TEXT,
                $COLUMN_BACKDROP_PATH TEXT,
                $COLUMN_GENDER_IDS TEXT
            )
        """
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Insert a movie
    fun insertMovie(movieItem: MovieItem) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID, movieItem.id)
            put(COLUMN_ORIGINAL_TITLE, movieItem.original_title)
            put(COLUMN_TITLE, movieItem.title)
            put(COLUMN_VOTE_AVERAGE, movieItem.vote_average)
            put(COLUMN_RELEASE_DATE, movieItem.release_date)
            put(COLUMN_BACKDROP_PATH, movieItem.backdrop_path)
            put(COLUMN_GENDER_IDS, movieItem.gender_ids?.joinToString(","))
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // Get all movies
    fun getAllMovies(): List<MovieItem> {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        val moviesList = mutableListOf<MovieItem>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID))
                val originalTitle = getString(getColumnIndexOrThrow(COLUMN_ORIGINAL_TITLE))
                val title = getString(getColumnIndexOrThrow(COLUMN_TITLE))
                val voteAverage = getDouble(getColumnIndexOrThrow(COLUMN_VOTE_AVERAGE))
                val releaseDate = getString(getColumnIndexOrThrow(COLUMN_RELEASE_DATE))
                val backdropPath = getString(getColumnIndexOrThrow(COLUMN_BACKDROP_PATH))
                val genderIdsString = getString(getColumnIndexOrThrow(COLUMN_GENDER_IDS))
                val genderIds = genderIdsString?.split(",")?.map { it.toInt() }

                val movieItem = MovieItem(
                    id,
                    originalTitle,
                    title,
                    voteAverage,
                    releaseDate,
                    backdropPath,
                    genderIds
                )
                moviesList.add(movieItem)
            }
        }
        cursor.close()
        db.close()
        return moviesList
    }
}
