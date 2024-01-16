package com.example.mrsu


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mrsu.databinding.PersonalAccountBinding
import com.example.mrsu.retrofit.Api_For_Connection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PersonalAccount : AppCompatActivity() {
    lateinit var binding:PersonalAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_account)
        binding = PersonalAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)


            val token = "Bearer "+ intent.getStringExtra("token").toString()
            val token1 = intent.getStringExtra("token").toString()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://papi.mrsu.ru")
                .addConverterFactory(GsonConverterFactory.create()).build()
            val mainApi = retrofit.create(Api_For_Connection::class.java)
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