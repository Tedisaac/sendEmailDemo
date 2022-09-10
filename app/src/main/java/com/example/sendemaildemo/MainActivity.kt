package com.example.sendemaildemo


import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sendemaildemo.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.Executors
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class MainActivity : AppCompatActivity() {

    private val activityMainBinding by lazy{ ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)


        activityMainBinding.sendEmail.setOnClickListener {
            sendEmail()
        }
    }

    private fun sendEmail() {
       //val sender = "ndeited@gmail.com"
        val receiver = "ndeited@gmail.com"
        val title = "Enquiry Feedback"
        val message = "Greetings, \n\n We have received your enquiry and we will get back you once we have some feedback \n\n Regards, \n\n Sian Realtors LTD."



       val properties = Properties()
        properties.put("mail.smtp.auth", "true")
        properties.put("mail.smtp.starttls.enable", "true")
        properties.put("mail.smtp.host", "smtp.gmail.com")
        properties.put("mail.smtp.port", "587")

        val session = Session.getInstance(properties, object: Authenticator(){
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(Utils().EMAIL, Utils().PASSWORD)
            }
        })

        try {
            val mimeMessage = MimeMessage(session)
            mimeMessage.setFrom(InternetAddress(Utils().EMAIL))
            mimeMessage.setRecipient(Message.RecipientType.TO,
            InternetAddress(receiver))
            mimeMessage.setSubject(title)
            mimeMessage.setText(message)


            val executor = Executors.newSingleThreadExecutor()
            val handler = Handler(Looper.getMainLooper())
            executor.execute {
               Transport.send(mimeMessage)
                handler.post {
                    Log.e(TAG, "sendEmail: HURRAY!!!!", )
                }
            }
            //GlobalScope.launch { Transport.send(mimeMessage) }
        }catch (e: Exception){
            Log.e(TAG, "sendEmail: ${e.message}", )
        }




    }
}