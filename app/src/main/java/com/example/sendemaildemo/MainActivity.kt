package com.example.sendemaildemo

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sendemaildemo.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.mail.Transport

class MainActivity : AppCompatActivity() {

    val activityMainBinding by lazy{ ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)


        activityMainBinding.sendEmail.setOnClickListener {
            sendEmail()
        }
    }

    private fun sendEmail() {
        val sender = "ndeited@gmail.com"
        val receiver = "sianrealtors.dev@gmail.com"
        val title = "Enquiry Feedback"
        val message = "Greetings, \n\n We have received your enquiry and we will get back you once we have some feedback \n\n Regards, \n\n Sian Realtors LTD."



        JavaMailAPI().sendEmailGoogle( receiver, title, message)




    }
}