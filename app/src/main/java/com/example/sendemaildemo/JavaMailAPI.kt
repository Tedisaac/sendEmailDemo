package com.example.sendemaildemo

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class JavaMailAPI{
    fun sendEmailYandex(receiver: String, subject: String, message: String ): MimeMessage? {
        val properties = Properties()


        properties.put("mail.smtp.host", "smtp.yandex.com")
        properties.put("mail.smtp.socketFactory.port", "587")
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        properties.put("mail.smtp.auth", "true")
        properties.put("mail.smtp.port", "993")

        val mSession = Session.getDefaultInstance(properties,
            object : Authenticator() {
                //Authenticating the password
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(Utils().EMAIL, Utils().PASSWORD)
                }
            })

        val mimeMessage = MimeMessage(mSession)
        try {

            mimeMessage.setFrom(InternetAddress(Utils().EMAIL))
            mimeMessage.addRecipient(Message.RecipientType.TO,InternetAddress(receiver))
            mimeMessage.setSubject(subject)
            mimeMessage.setText(message)

        }catch (e: Exception){
            Log.e(TAG, "sendEmailFunction: $receiver", )
            Log.e(TAG, "sendEmailFunction: $subject", )
            Log.e(TAG, "sendEmailFunction: $message", )
            Log.e(TAG, "sendEmailFunction: ${e.localizedMessage}", )
        }
        return mimeMessage
    }

    fun sendEmailGoogle(receiver: String, subject: String, message: String ){
        val stringHost = "smtp.gmail.com"
        val stringPort = "465"
        val stringSSL = "true"
        val stringAuth = "true"

        val email = "ndeited@gmail.com"
        val password = "3839ndei"

        val properties = System.getProperties()

        properties.put("mail.smtp.host", stringHost)
        properties.put("mail.smtp.port", stringPort)
        properties.put("mail.smtp.ssl.enable", stringSSL)
        properties.put("mail.smtp.auth", stringAuth)

        val session = Session.getInstance(properties, object: Authenticator(){
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(email, password)
            }
        })

        val mimeMessage = MimeMessage(session)

        mimeMessage.addRecipient(Message.RecipientType.TO, InternetAddress(receiver))
        mimeMessage.setSubject(subject)
        mimeMessage.setText(message)
        
        try {
            GlobalScope.launch { 
                mimeMessage
            }
        }catch (e: MessagingException){
            Log.e(TAG, "sendEmailGoogle: ${e.message}", )
        }
    }


}