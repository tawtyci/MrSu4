package com.example.mrsu


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrsu.domain.models.TimeTableToDate
import com.example.mrsu.databinding.SheduleOfDisciplinesBinding
import com.example.mrsu.retrofit.Api_For_Connection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class SheduleOfDisciplines : AppCompatActivity() {
    lateinit var binding: SheduleOfDisciplinesBinding
    private val adapterCal = Adapter_For_Shedule()
    private val adapterTb = Adapter_For_Shedule()
    val dateFormat = "MMMM dd, yyyy"
    val sdf = java.text.SimpleDateFormat(dateFormat, Locale.getDefault())
    var date = sdf.format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shedule_of_disciplines)
        binding = SheduleOfDisciplinesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttCalendar.text = date
        val token = "Bearer "+ intent.getStringExtra("token").toString()
        val token1 = intent.getStringExtra("token").toString()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://papi.mrsu.ru")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainApi = retrofit.create(Api_For_Connection::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val timetable = mainApi.getTimeTable(date, token)
            runOnUiThread {
                binding.facInfo.text = timetable.get(1).FacultyName + "(${timetable.get(1).Group})";
                binding.apply {
                    RWCalendar.layoutManager = LinearLayoutManager(this@SheduleOfDisciplines)
                    RWCalendar.adapter = adapterCal
                    adapterCal.addTB(timetable.get(1))
                }
            }
            runOnUiThread {
                binding.YMYName.text = timetable.get(0).FacultyName + "(${timetable.get(0).Group})";
                binding.apply {
                    RWTb.layoutManager = LinearLayoutManager(this@SheduleOfDisciplines)
                    RWTb.adapter = adapterTb
                    adapterTb.addTB(timetable.get(0))
                }
            }
            binding.Calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)

                val dateFormat = "yyyy-MM-dd"
                val sdf = java.text.SimpleDateFormat(dateFormat, Locale.getDefault())


                val newDate = sdf.format(selectedDate.time)
                val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(newDate)
                val formattedDateString = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(formattedDate)

                binding.buttCalendar.text = formattedDateString

                CoroutineScope(Dispatchers.IO).launch {
                    val newTimetable = mainApi.getTimeTable(newDate, token)
                    runOnUiThread {
                        updateAdapter(adapterCal,newTimetable.get(1)) // Обновляем адаптер с новыми данными // Обновляем адаптер с новыми данными
                        updateAdapterUmu(adapterTb,newTimetable.get(0)) // Обновляем адаптер с новыми данными // Обновляем адаптер с новыми данными
                    }

                }
                binding.Calendar.visibility = View.GONE
                var isCalendarViewVisible = false
                binding.buttCalendar.setOnClickListener {
                    if (isCalendarViewVisible) {
                        binding.Calendar.visibility = View.GONE
                        isCalendarViewVisible = false
                        // binding.RWCalendar.visibility = View.VISIBLE
                    } else {
                        binding.Calendar.visibility = View.VISIBLE
                        isCalendarViewVisible = true
                        // binding.RWCalendar.visibility = View.GONE
                    }
                }
            }
        }
        binding.bNav.setOnNavigationItemSelectedListener() {
            when (it.itemId) {
                R.id.lk -> {
                    val intent = Intent(this, PersonalAccount::class.java)
                    intent.putExtra("token", token1)
                    startActivity(intent)
                }
            }
            true
        }

    }
    private fun updateAdapter(adapter: Adapter_For_Shedule, newData: TimeTableToDate) { // Замените YourTimetableDataType на тип данных вашего расписания
        binding.facInfo.text = newData.FacultyName + "(${newData.Group})"
        binding.apply {
            RWCalendar.layoutManager = LinearLayoutManager(this@SheduleOfDisciplines)
            RWCalendar.adapter = adapter // Устанавливаем обновленные данные в адаптер
            adapter.addTB(newData)
        }
    }
    private fun updateAdapterUmu(adapter: Adapter_For_Shedule, newData: TimeTableToDate) { // Замените YourTimetableDataType на тип данных вашего расписания
        binding.YMYName.text = newData.FacultyName + "(${newData.Group})"
        binding.apply {
            RWTb.layoutManager = LinearLayoutManager(this@SheduleOfDisciplines)
            RWTb.adapter = adapter // Устанавливаем обновленные данные в адаптер
            adapter.addTB(newData)
        }
    }
}