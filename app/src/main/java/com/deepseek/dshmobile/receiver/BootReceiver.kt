package com.deepseek.dshmobile.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "BootReceiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED,
            "android.intent.action.QUICKBOOT_POWERON" -> {
                Log.i(TAG, "Boot completed, starting engine...")
                val serviceIntent = Intent(context, com.deepseek.dshmobile.service.DshEngineService::class.java)
                serviceIntent.action = com.deepseek.dshmobile.service.DshEngineService.ACTION_START
                context.startService(serviceIntent)
            }
        }
    }
}
