package com.example.mrsu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mrsu.databinding.HomePageBinding
import com.example.mrsu.retrofit.Api_For_Connection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomePage : AppCompatActivity() {
    lateinit var binding: HomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
        binding = HomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val intent = Intent(this, PersonalAccount::class.java)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://p.mrsu.ru")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val mainApi = retrofit.create(Api_For_Connection::class.java)
        binding.buttReg.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
               try {
                   val token = mainApi.getAccessToken(
                       "password",
                       "8",
                       "qweasd",
                       "чадовааа",
                       "NASTYHA55"
                       //binding.username.text.toString(),
                       //binding.userPassword.text.toString()
                   )
                   runOnUiThread {
                       intent.putExtra("token", token.access_token)
                       startActivity(intent)
                   }
               }
               catch (e: HttpException) {
                   runOnUiThread {
                       if (e.code() == 400) {
                           binding.errLog.text = "Неправильно введены логин или пароль!"
                       } else {
                           binding.errLog.text = "Ошибка сервера!"
                       }
                   }
               }

            }

        }


    }

}
