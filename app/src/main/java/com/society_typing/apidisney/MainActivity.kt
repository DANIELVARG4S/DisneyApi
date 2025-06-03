package com.society_typing.apidisney

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.society_typing.apidisney.Adapter.DisneyAdapter
import com.society_typing.apidisney.Data.DisneyDataResponse
import com.society_typing.apidisney.DetailDisneyActivity.Companion.EXTRA_ID
import com.society_typing.apidisney.Service.ApiService
import com.society_typing.apidisney.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: DisneyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        adapter = DisneyAdapter { disneyId ->
            navigateToDetail(disneyId)
        }
        binding.rvDisney.setHasFixedSize(true)
        binding.rvDisney.layoutManager = LinearLayoutManager(this)
        binding.rvDisney.adapter = adapter
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<DisneyDataResponse> =
                retrofit.create(ApiService::class.java).getCharacterDisney(query)
            if (myResponse.isSuccessful) {
                val response: DisneyDataResponse? = myResponse.body()
                if (response != null) {
                    Log.i("dani", response.toString())
                    runOnUiThread {
                        adapter.updateList(response.result)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("dani", "no funciono")
            }
        }
    }

    //LLAMADA A RETROFIT
    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://api.disneyapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id: Int) {
        val intent = Intent(this, DetailDisneyActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}