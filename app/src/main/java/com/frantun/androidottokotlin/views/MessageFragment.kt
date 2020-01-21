package com.frantun.androidottokotlin.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frantun.androidottokotlin.R
import com.frantun.androidottokotlin.otto.Events
import com.frantun.androidottokotlin.otto.GlobalBus
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onStart() {
        super.onStart()

        GlobalBus.bus.register(this)
    }

    override fun onStop() {
        super.onStop()

        GlobalBus.bus.unregister(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnSendMessage.setOnClickListener {
            sendMessageToActivity()
        }
    }

    private fun sendMessageToActivity() {
        val fragmentActivityMessageEvent =
            Events.FragmentActivityMessage(edtMessage.text.toString())

        GlobalBus.bus.post(fragmentActivityMessageEvent)
    }

    @Subscribe
    fun getMessage(activityFragmentMessage: Events.ActivityFragmentMessage) {
        val messageReceived =
            getString(R.string.message_received) + " " + activityFragmentMessage.message
        txtMessageReceived.text = messageReceived
    }
}