package com.example.catapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var imgView: ImageView
    //val logo = findViewById<TextView>(R.id.logo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgView = findViewById(R.id.imageView)

        val searchButton: Button = findViewById(R.id.searchButton)


        searchButton.setOnClickListener {
            loadRandomCat()
        }
    }
    private fun loadRandomCat() {

        val retrofitCat = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/images/search")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val catService: CatService = retrofitCat.create(CatService::class.java)

        val callCat = catService.randomCat().enqueue(object : Callback<List<Cat>> {
            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                val randomCat = response.body()?.get(0) // Get the first cat from the list
                randomCat?.let {
                    Picasso.get()
                        .load(it.url)
                        .into(imgView)
                }
            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                // Handle failure (e.g., network error)
            }
        })
    }
}




