package com.example.mrsu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mrsu.domain.models.TimeTableToDate
import com.example.mrsu.databinding.ShablonTimeTableBinding
import com.squareup.picasso.Picasso

class TB_Adapter : RecyclerView.Adapter<TB_Adapter.TbHolder>() {
    val tbList = ArrayList<TimeTableToDate>()

    class TbHolder(item: View):RecyclerView.ViewHolder(item) {
        val binding = ShablonTimeTableBinding.bind(item)
        fun bind(timeTable: TimeTableToDate, position: Int) = with(binding){
            twObject.text = timeTable.TimeTable?.Lessons?.get(position)?.Disciplines?.get(0)?.Title
            tvFio.text= timeTable.TimeTable?.Lessons?.get(position)?.Disciplines?.get(0)?.Teacher?.UserName
            Picasso.get().load(timeTable.TimeTable?.Lessons?.get(position)?.Disciplines?.get(0)?.Teacher?.Photo?.UrlSource).into(imPhoto)

            val para = timeTable.TimeTable?.Lessons?.get(position)?.Number
            val time = table(para)

            val cabinet = timeTable.TimeTable?.Lessons?.get(position)?.Disciplines?.get(0)?.Auditorium?.Number

            ShTime.text = "$time , ${cabinet.toString()}"
        }

        fun table(n: Byte?): String {
            val times: Map<Byte, String> = mapOf(
                1.toByte() to "8:30 - 10:00",
                2.toByte() to "10:10 - 11:40",
                3.toByte() to "12:00 - 13:30",
                4.toByte() to "13:45 - 15:15",
                5.toByte() to "15:25 - 16:55",
                6.toByte() to "17:05 - 18:35",
                7.toByte() to "18:40 - 20:10"
            )
            return times[n] ?: "error"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TbHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shablon_time_table,parent,false)
        return TbHolder(view)
    }

    override fun getItemCount(): Int {
        return tbList.get(0).TimeTable!!.Lessons.size
    }

    override fun onBindViewHolder(holder: TbHolder, position: Int) {
        holder.bind(tbList[0],position)
    }
    fun addTB(timeTable: TimeTableToDate){
        tbList.clear()
        tbList.add(timeTable)
        notifyDataSetChanged()
    }


}