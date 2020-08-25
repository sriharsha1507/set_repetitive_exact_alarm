package com.foodiedev.setrepetitiveexactalarm

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.foodiedev.setrepetitiveexactalarm.service.AlarmService
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var alarmService: AlarmService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        alarmService = AlarmService(this)

        setExact.setOnClickListener {
            Calendar.getInstance().apply {
                DatePickerDialog(
                    this@MainActivity,
                    0,
                    DatePickerDialog.OnDateSetListener { _, year, month, day ->
                        this.set(Calendar.YEAR, year)
                        this.set(Calendar.MONTH, month)
                        this.set(Calendar.DAY_OF_MONTH, day)
                        TimePickerDialog(
                            this@MainActivity,
                            0,
                            TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                                this.set(Calendar.HOUR_OF_DAY, hour)
                                this.set(Calendar.MINUTE, minute)

                                alarmService.setExactAlarm(timeInMillis)
                            },
                            this.get(Calendar.HOUR_OF_DAY),
                            this.get(Calendar.MINUTE),
                            false
                        ).show()
                    },
                    this.get(Calendar.YEAR),
                    this.get(Calendar.MONTH),
                    this.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
    }
}