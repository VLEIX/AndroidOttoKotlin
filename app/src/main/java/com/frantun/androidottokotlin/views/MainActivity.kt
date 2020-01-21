package com.frantun.androidottokotlin.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.frantun.androidottokotlin.R
import com.frantun.androidottokotlin.otto.Events
import com.frantun.androidottokotlin.otto.GlobalBus
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.fragment_message.btnSendMessage
import kotlinx.android.synthetic.main.fragment_message.edtMessage
import kotlinx.android.synthetic.main.fragment_message.txtMessageReceived

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment()

        btnSendMessage.setOnClickListener {
            sendMessageToFragment()
        }
    }

    override fun onStart() {
        super.onStart()

        GlobalBus.bus.register(this)
    }

    override fun onStop() {
        super.onStop()

        GlobalBus.bus.unregister(this)
    }

    private fun addFragment() {
        supportFragmentManager.beginTransaction().add(R.id.frmContainer, MessageFragment()).commit()
    }

    private fun sendMessageToFragment() {
        val activityFragmentMessage =
            Events.ActivityFragmentMessage(edtMessage.text.toString())

        GlobalBus.bus.post(activityFragmentMessage)
    }

    @Subscribe
    fun getMessage(fragmentActivityMessage: Events.FragmentActivityMessage) {
        val messageReceived =
            getString(R.string.message_received) + " " + fragmentActivityMessage.message
        txtMessageReceived.text = messageReceived
    }
}
