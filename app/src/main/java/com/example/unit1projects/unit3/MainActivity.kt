package com.example.unit1projects.unit3

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unit1projects.R
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var postList: List<Post>

    private val retrofitInstance by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiService: ApiService by lazy {
        retrofitInstance.create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        postsRecyclerView = findViewById(R.id.postList)
        postsRecyclerView.layoutManager = LinearLayoutManager(this)

//        lifecycleScope.launch {
//            try {
//                val posts: List<Post> = apiService.getPosts()
//            } catch(e: Exception) {
//                e.printStackTrace()
//            }
//        }

        apiService.getPosts().enqueue(object: Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts: List<Post>? = response.body()
                    if (posts != null) {
                        postList = posts
                        postsAdapter = PostsAdapter(postList)
                        postsRecyclerView.adapter = postsAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("MainActivity", t.message.toString())
            }
        })
    }
}