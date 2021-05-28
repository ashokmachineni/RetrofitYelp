package com.applaunch.yelp

import com.google.gson.annotations.SerializedName

data class YelpSearchResult(
        @SerializedName("total") val total:Int,
        @SerializedName("businesses") val restaursnts:List<YelpRestaurant>
)
data class YelpRestaurant(
        @SerializedName("name") val name: String,
        val rating:Double,
        val price:String,
        val review_count:Int,
        val distance:Double,
        val image_url:String
//        @SerializedName("distance") val distanceInMeters:Double,
//        val location:YelpLocation
)
//fun displayDistance():String{
//    val milesPerMeter = 0.000621
//    val distanceInMiles = "%.2f".format(distanceInMeters*milesPerMeter)
//    return  distanceInMiles
//}
data class YelpLocation(
        @SerializedName("address1") val address:String
)