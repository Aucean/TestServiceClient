package com.example.aucean.testserviceclient

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {

    var messager: Messenger? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startTestService(v: View) {
        val inte = Intent()
        inte.component = ComponentName("com.example.aucean.testservicea","com.example.aucean.testservicea.TestService")
        inte.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        bindService(inte, myService, BIND_AUTO_CREATE)
    }

    val myService = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            messager = Messenger(service)
            val msg = Message()
            msg.what = 1
            messager?.send(msg)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i("ServiceClient", "service disconnected")
        }
    }
}
