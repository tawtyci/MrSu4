package com.example.mrsu


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mrsu.databinding.ActivityMainLkBinding
import com.example.mrsu.retrofit.MainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PersonalAccount : AppCompatActivity() {
    lateinit var binding:ActivityMainLkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_lk)
        binding = ActivityMainLkBinding.inflate(layoutInflater)
        setContentView(binding.root)


            val token = "Bearer "+ intent.getStringExtra("token").toString()
            val token1 = intent.getStringExtra("token").toString()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://papi.mrsu.ru")
                .addConverterFactory(GsonConverterFactory.create()).build()
            val mainApi = retrofit.create(MainApi::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val user = mainApi.getUser(token)
                runOnUiThread{
                    binding.tvFIO.text= user.FIO
                    binding.tvId.text = "id: "+ user.StudentCod

                }
        }
        binding.bNav.setOnNavigationItemSelectedListener() {
            when(it.itemId) {
                R.id.calendar -> {
                    val intent = Intent(this, SheduleOfDisciplines::class.java)
                    intent.putExtra("token", token1)
                    startActivity(intent)
                }
            }
            true
        }



    }
}