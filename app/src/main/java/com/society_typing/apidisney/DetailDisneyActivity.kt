package com.society_typing.apidisney

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.society_typing.apidisney.Data.DisneyDetailResponse
import com.society_typing.apidisney.Service.ApiService
import com.society_typing.apidisney.databinding.ActivityDetailDisneyBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailDisneyActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailDisneyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDisneyBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        val characterId  = intent.getIntExtra(EXTRA_ID, -1)
        if (characterId != -1) {
            getDisneyInformation(characterId)
        } else {
            Log.i("no encontro valor de ", "${characterId}")
        }
        //getDisneyInformation(id.toInt())
    }

    private fun getDisneyInformation(characterId: Int) {

        CoroutineScope(Dispatchers.IO).launch {
            val disneyDetail= getRetrofit().create(ApiService::class.java).getDisneyDetail(characterId)
               if (disneyDetail.body() != null){
                   Log.i("DisneyInformation ", "${disneyDetail}")
                    runOnUiThread { createUI(disneyDetail.body()!!) }
               }
        }
    }
    //TODO
    private fun createUI(disney: DisneyDetailResponse){
        //Log.i("createUI ", "${disney}")
        Picasso.get().load(disney.imageUrl).into(binding.ivDisney)
        binding.tvDisneyName.text = disney.name

    }

    //LLAMADA A RETROFIT
    private fun  getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://api.disneyapi.dev")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}