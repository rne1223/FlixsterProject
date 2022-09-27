package com.codepath.bestsellerlistapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject

// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
//private const val API_KEY = "EeNDb39VWWhGxunyJSOb8KE2RI3SOWJE"
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val BASE_URL = "https://api.themoviedb.org/3/movie/"
private const val END_POINT = "now_playing?language=en-US&page=1&api_key="



/*
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to the NY Times API.
 */
class MoviesFragment : Fragment(), OnListFragmentInteractionListener {

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movies_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show() // Show the progressbar

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY


        val url: String = BASE_URL + END_POINT + API_KEY
        Log.v("MoviesFragment", url)
        // Using the client, perform the HTTP request
        client[ url,
            params,
            object : JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers, json: JSON?) {

                    progressBar.hide() // Hide the progressbar
                    val resultJSON: JSONObject = json?.jsonObject as JSONObject
                    val movieRawJson: String = resultJSON.get("results").toString()

                    Log.v("MoviesFragment", movieRawJson.toString())

                    val gson = Gson()
                    val arrayMovie = object : TypeToken<List<Movie>>() {}.type
                    val models: List<Movie> = gson.fromJson(movieRawJson, arrayMovie )
                    Log.v("MoviesFragment", models.toString())

                    /**
                    val resultsJSON : JSONObject = json?.jsonObject?.get("results") as JSONObject
                    val booksRawJson: String = resultsJSON.get("books").toString()

                    val gson = Gson()
                    val arrayBookType = object : TypeToken<List<Movie>>() {}.type
                    val models: List<Movie> = gson.fromJson(booksRawJson, arrayBookType)
                     */

                    recyclerView.adapter = MoviesRecyclerViewAdapter(models, this@MoviesFragment)

                    Log.d("BestSellerBooksFragment", "response successful")
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    // The wait for a response is over
                    progressBar.hide()

                    // If the error is not null, log it!
                    t?.message?.let {
                        Log.e("BestSellerBooksFragment", errorResponse)
                    }
                }

            }]
    }

    /*
     * What happens when a particular book is clicked.
     */
    override fun onItemClick(item: Movie) {
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
    }

}
