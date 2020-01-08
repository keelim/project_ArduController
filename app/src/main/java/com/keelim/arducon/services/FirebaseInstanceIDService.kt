package com.keelim.arducon.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.keelim.arducon.R
import com.keelim.arducon.activities.MainActivity

class FirebaseInstanceIDService : FirebaseMessagingService() {
    override fun onNewToken(s: String) {
        super.onNewToken(s)
        Log.e("Firebase", "FirebaseInstanceIDService : $s")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) { // 메시지 수신 시 실행되는 메소드
        if (remoteMessage.data.size > 0) {
            sendNotification(remoteMessage)
        }
    }

    /**
     * 메시지가 수신되었을 때 실행되는 메소드
     */
    private fun sendNotification(remoteMessage: RemoteMessage) {
        val message = remoteMessage.data["data"]
        // 수신되는 푸시 메시지
        val messageDivider = message!!.indexOf("|")
        // 구분자를 통해 어떤 종류의 알람인지를 구별합니다.
        val pushType = message.substring(messageDivider + 1) // 구분자 뒤에 나오는 메시지
        val resultIntent = Intent(this, MainActivity::class.java)
        resultIntent.putExtra("pushType", pushType)
        val pendingIntent = PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = "채널"
            val channel_nm = "채널 이름" // 앱 설정에서 알림 이름으로 뜸.
            val notichannel = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelMessage = NotificationChannel(channel, channel_nm,
                    NotificationManager.IMPORTANCE_DEFAULT)
            channelMessage.description = "채널에 대한 설명입니다."
            channelMessage.enableLights(true)
            channelMessage.enableVibration(true)
            channelMessage.setShowBadge(false)
            channelMessage.vibrationPattern = longArrayOf(100, 200, 100, 200)
            notichannel.createNotificationChannel(channelMessage)
            val notificationBuilder = NotificationCompat.Builder(this, channel)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("알림이 왔어요!")
                    .setContentText(message.substring(0, messageDivider))
                    .setChannelId(channel)
                    .setAutoCancel(true)
                    .setColor(Color.parseColor("#0ec874"))
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(9999, notificationBuilder.build())
        } else {
            val notificationBuilder = NotificationCompat.Builder(this, "")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("푸시 타이틀")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setColor(Color.parseColor("#0ec874")) // 푸시 색상
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(9999, notificationBuilder.build())
        }
    }
}