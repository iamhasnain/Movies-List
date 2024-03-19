package com.app.movieslist.ui.movie

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.movieslist.data.repository.RemoteMovieRepository
import com.app.movieslist.databinding.ActivityMovieListBinding
import com.app.movieslist.errorhandling.ToastErrorHandler
import com.app.movieslist.model.Movie
import com.app.movieslist.network.RetrofitClient
import com.app.movieslist.ui.moviesdetails.MovieDetailsActivity

class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding
    private lateinit var viewModel: MovieListViewModel
    private lateinit var adapter: MovieListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater) // Inflate the binding layout
        setContentView(binding.root)
        val movieRepository = RemoteMovieRepository(RetrofitClient.apiService)
        val errorHandler = ToastErrorHandler(this@MovieListActivity)

        viewModel = ViewModelProvider(this, MovieListViewModelFactory(movieRepository, errorHandler))[MovieListViewModel::class.java]

        binding.recyclerViewMovies.layoutManager = LinearLayoutManager(this)
        adapter = MovieListAdapter()
        binding.recyclerViewMovies.adapter = adapter
        adapter.setOnItemClickListener(object : MovieListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, movie: Movie) {
                val intent = Intent(this@MovieListActivity, MovieDetailsActivity::class.java)
                intent.putExtra("movieId", movie.id)
                startActivity(intent)
            }

        })

        // Observe movies LiveData
        viewModel.movies.observe(this, Observer {
            // Update UI with movies
            adapter.setData(it.results)
        })

        // Fetch movies
        viewModel.fetchMovies(this@MovieListActivity)

        binding.editTextSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    filterMovies(binding.editTextSearch.text.toString())
                    true
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun filterMovies(query: String) {
        if (adapter!=null)
            adapter.filterMovies(query)
    }
}