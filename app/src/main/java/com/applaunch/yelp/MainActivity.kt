package com.applaunch.yelp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL="https://api.yelp.com/v3/"
private const val TAG ="mills"
private const val API_KEY="qufNTGZw8IaA20s5TOs4Xs4Grn_C8KiO4c_nTnQggI1nxIjRr4IXtJ8bchiaz0TONJWOFO00yYoHKyC_uQaOJagZk6RJ21psibLvzxnTPAkwb24L6Pu0apILMI2NYHYx"
class MainActivity : AppCompatActivity() {

//    val restaurants = mutableListOf<YelpRestaurant>()
//    val adapter = RestaurantAdapter(this,restaurants)
  
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val restaurants = mutableListOf<YelpRestaurant>()
        val adapter = RestaurantAdapter(this,restaurants)
        val recyclerView = findViewById<RecyclerView>(R.id.rvRest)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val yelpService = retrofit.create(YelpService::class.java)
        yelpService.searchRestaurants("Bearer $API_KEY","Biryani","Leicester").enqueue(object : Callback<YelpSearchResult>{
            override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>) {
                Log.i("main","Respon: $response")
                val body = response.body()
                if (body == null){
                    Log.d("err","Some thing wend wrong")
                }else{
                    restaurants.addAll(body.restaursnts)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i("main","fail : $t")
            }


//            override fun onResponse(
//                call: Call<YelpSearchResult>,
//                response: Response<YelpSearchResult>
//            ) {
//                Log.i("main","Respon: $response")
//            }
//
//            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
//                Log.i("main","fail : $t")
//            }

        })

    }
}