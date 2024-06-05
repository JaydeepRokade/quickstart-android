package com.google.firebase.example.dataconnect.data

import com.google.firebase.dataconnect.movies.MoviesConnector
import com.google.firebase.dataconnect.movies.execute
import com.google.firebase.dataconnect.movies.instance

class MovieRepository(
    private val moviesConnector: MoviesConnector = MoviesConnector.instance
) {

    // Repositories
    suspend fun listMovies(): List<Movie> {
        return moviesConnector.listMovies.execute().data.movies.map { it.toMovie() }
    }

    suspend fun getTop10Movies(): List<Movie> {
        return moviesConnector.moviesTop10.execute().data.movies.map { it.toMovie() }
    }

    suspend fun getRecentlyReleasedMovies(): List<Movie> {
        return moviesConnector.moviesRecentlyReleased.execute().data.movies.map { it.toMovie() }
    }

    suspend fun getMoviesByGenre(genre: String): MoviesByGenre {
        val data = moviesConnector.listMoviesByGenre.execute(genre).data
        val mostPopular = data.mostPopular.map { it.toMovie() }
        val mostRecent = data.mostRecent.map { it.toMovie() }
        return MoviesByGenre(mostPopular, mostRecent)
    }

    // Mutations
}