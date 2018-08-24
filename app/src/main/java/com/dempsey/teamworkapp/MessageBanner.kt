package com.dempsey.mytaxiapplication.utils

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.dempsey.teamworkapp.R

class MessageBanner(private val activity: AppCompatActivity) {

    fun showBanner(textResId: Int, messageType: MessageType) {
        if (activity.isFinishing) {
            return
        }
        showBanner(activity.getString(textResId), messageType)
    }

    fun showBanner(message: String, messageType: MessageType) {
        if (activity.isFinishing) {
            return
        }

        val backgroundRes = if (messageType === MessageType.SUCCESS)
            R.drawable.message_banner_success_background else R.drawable.message_banner_error_background
        val rootView = (activity.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup

        val snackBar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundResource(backgroundRes)
        snackBar.show()
    }
}