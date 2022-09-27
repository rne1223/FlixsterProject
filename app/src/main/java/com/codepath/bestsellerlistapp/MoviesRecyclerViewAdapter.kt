package com.codepath.bestsellerlistapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.bestsellerlistapp.R.id

/**
 * [RecyclerView.Adapter] that can display a [Movie] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MoviesRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?
    )
    : RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder>()
    {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null
        val mPoster: ImageView = mView.findViewById<View>(id.book_image) as ImageView
        val mMovieTitle: TextView = mView.findViewById<View>(id.book_title) as TextView
        val mMovieOverview: TextView = mView.findViewById<View>(id.book_description) as TextView
        val mMovieText: TextView = mView.findViewById<View>(id.textView) as TextView

        /**
        val mBookAuthor: TextView = mView.findViewById<View>(id.book_author) as TextView
        val mBookRanking: TextView = mView.findViewById<View>(id.ranking) as TextView
        val mBookButton: Button = mView.findViewById<View>(id.buy_button) as Button
         */


        override fun toString(): String {
            return mMovieTitle.toString() + " '"
        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        Log.v("MoviesRecyclerAdapter", movie.movieOverview.toString())

        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieOverview.text = "Hello World"
        holder.mMovieText.text = movie.movieOverview
        Log.v("MoviesRecyclerAdapter", holder.mMovieOverview.text.toString())

        // Put image in the view
        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/" + movie.moviePosterUrl)
            .centerInside()
            .into(holder.mPoster)
        /**
        holder.mBookAuthor.text = movie.author
        holder.mBookRanking.text = movie.rank.toString()
        holder.mBookDescription.text = movie.description


        // Put image in the view
        Glide.with(holder.mView)
            .load(book.bookImageUrl)
            .centerInside()
            .into(holder.mBookImage)

        // Click handler for the
        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }
        }

        holder.mBookButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(book.amazonUrl))
            startActivity(it.context, browserIntent, null)
        }
        */
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return movies.size
    }
}