package dev.SE

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
  private var tvSelectedDate: TextView? = null
  private var tvAgeInMinutes:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatepicker:Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes=findViewById(R.id.tvAgeInMinutes)

        btnDatepicker.setOnClickListener {
            ClickDate()
        }
    }

    fun ClickDate()
    {
        val mycalendar = Calendar.getInstance()
        val year = mycalendar.get(Calendar.YEAR)
        val month = mycalendar.get(Calendar.MONTH)
        val day = mycalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener
        { _, year, month, dayOfMonth ->
            Toast.makeText(this,"Year was $year,month was ${month+1}"
                ,Toast.LENGTH_LONG).show()

            val selectedDate ="$dayOfMonth/${month+1}/$year"

            tvSelectedDate?.text=selectedDate

            val sdf  = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH )
            val date = sdf.parse(selectedDate)
            date?.let {
                val selectedDateInMinutes=date.time/60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInminutes= currentDate.time/60000

                    val differenceInMinutes=currentDateInminutes-selectedDateInMinutes

                    tvAgeInMinutes?.text = differenceInMinutes.toString()
                }

            }


        },
            year,month,day)
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
     dpd.show()

    }

}