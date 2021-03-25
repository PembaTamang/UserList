package com.example.userlist

import ApiResponse
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.userlist.databinding.ActivityMainBinding
import com.example.userlist.models.Data
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(), Callback {
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var binding : ActivityMainBinding
    private lateinit var recyclerAdapter: RecyclerAdapter
    var arrayList = ArrayList<Data> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        if (hasInternet(applicationContext)) {
          getData()
        } else {
            Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
        }
    }

    private fun getData() {
        binding.progress.visibility = View.VISIBLE
        okHttpClient = OkhttpInstance.getInstance()
        val request = Request.Builder()
            .url(OkhttpInstance.url)
            .build()
        okHttpClient.newCall(request).enqueue(this)
    }

    private fun setUpRecyclerView() {
        recyclerAdapter = RecyclerAdapter(arrayList){
            Toast.makeText(this, "Item number $it ", Toast.LENGTH_SHORT).show()
        }
        binding.recycler.adapter = recyclerAdapter
    }

    override fun onFailure(call: Call, e: IOException) {
        mlog(e.stackTrace.toString())
        runOnUiThread {
            binding.progress.visibility = View.GONE
            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResponse(call: Call, response: Response) {
       val apiresponse : ApiResponse  = Gson().fromJson(response.body!!.string(),ApiResponse::class.java)
        mlog("response list ${apiresponse.data.size}")
        arrayList.clear()
        arrayList.addAll(apiresponse.data).also {
            runOnUiThread {
                binding.progress.visibility = View.GONE
                recyclerAdapter.notifyDataSetChanged()
            }
        }
    }
}