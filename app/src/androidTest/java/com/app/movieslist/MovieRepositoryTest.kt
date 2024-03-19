package com.app.movieslist

import com.app.movieslist.model.Movie
import com.app.movieslist.data.repository.MovieRepository
import com.app.movieslist.network.MovieApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
//import org.mockito.Mock
//import org.mockito.Mockito.`when`
//import org.mockito.MockitoAnnotations

class MovieRepositoryTest {

//    @Mock
//    private lateinit var movieService: MovieApiService
//
//    private lateinit var movieRepository: MovieRepository
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        movieRepository = MovieRepository(movieService)
//    }
//
//    @Test
//    fun `test fetching movies from API`() = runBlocking {
//        // Mock response
//        val expectedMovies = listOf(
//            Movie(1, "Movie 1", "Overview 1"),
//            Movie(2, "Movie 2", "Overview 2")
//        )
//        `when`(movieService.getMovies()).thenReturn(expectedMovies)
//
//        // Call repository method
//        val actualMovies = movieRepository.getMovies()
//
//        // Verify result
//        assertEquals(expectedMovies, actualMovies)
//    }
}
