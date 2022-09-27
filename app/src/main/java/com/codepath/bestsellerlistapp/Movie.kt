package com.codepath.bestsellerlistapp

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single book from the NY Times API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class Movie {

    @SerializedName("title")
    var title:String? = null

    @SerializedName("poster_path")
    var moviePosterUrl: String? = null

    @SerializedName("overview")
    var movieOverview: String? = null
    /**
    @SerializedName("rank")
    var rank = 0


    @JvmField
    @SerializedName("author")
    var author: String? = null

    //TODO bookImageUrl


    //TODO description
    @SerializedName("description")
    var description: String? = null


    //TODO-STRETCH-GOALS amazonUrl
    @SerializedName("amazon_product_url")
    var amazonUrl: String? = null
    */

}