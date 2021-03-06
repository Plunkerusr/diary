package com.example.slava.projectkek.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.slava.projectkek.R
import com.example.slava.projectkek.data.PreferencesHelper
import com.example.slava.projectkek.domain.utils.TextAdder
import khttp.get
import kotlinx.android.synthetic.main.activity_diary.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DiaryActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val context  = this
        val token = PreferencesHelper.getSharedPreferenceString(this, PreferencesHelper.KEY_TOKEN , "error")


        doAsync {
            val responseDiary = get("https://api.eljur.ru/api/getdiary/?auth_token=$token&devkey=8227490faaaa60bb94b7cb2f92eb08a4&vendor=hselyceum&out_format=json&rings=yes")
            val lol = get("https://api.eljur.ru/api/getassessments/?auth_token=$token&devkey=8227490faaaa60bb94b7cb2f92eb08a4&vendor=hselyceum&out_format=json&rings=yes")


            uiThread {

                val dr = responseDiary.jsonObject.getJSONObject("response").getJSONObject("result").getJSONObject("students").
                        getJSONObject("21554").getJSONObject("days")




               TextAdder.addDiaryBlock(schedule_container, context, dr)
            }
        }
    }


}