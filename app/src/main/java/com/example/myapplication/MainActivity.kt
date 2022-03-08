package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview_main.layoutManager = LinearLayoutManager(this)
        fetchData()

    }

    fun fetchData(){
        println("attempting to fetch")
        val url = "https://newsapi.org/v2/everything?q=tesla&from=2022-02-08&sortBy=publishedAt&apiKey=fbb3117f2cc44a95bb94462eed831686"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val homefeed = gson.fromJson(body, model :: class.java)


                runOnUiThread{
                    recyclerview_main.adapter = MainAdapter(homefeed)
                }
            }
        })
    }
}

class model(val articles : List<articles> )

class articles(val author :String, val  name:String,val url : String, val urlToImage : String)
