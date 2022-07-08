package danp.lab08.firebasecloudmessaging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage){
        if (remoteMessage.notification!=null){
            generateNotification(remoteMessage.notification!!.title!!,remoteMessage.notification!!.body!!)
        }
    }

    fun getRemoteView(title: String,message:String):RemoteViews{
        val remoteView = RemoteViews(packageName,R.layout.fmc_notification)
        remoteView.setTextViewText(R.id.title,title)
        remoteView.setTextViewText(R.id.message,message)

        val intent_HoraPeru = Intent(this, MainActivity::class.java).apply {
            putExtra("hora","Peru")
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        val pending_Peru = PendingIntent.getActivity(this,0,intent_HoraPeru,PendingIntent.FLAG_UPDATE_CURRENT)

        val intent_HoraChina = Intent(this,RelojAparte::class.java).apply {
            putExtra("hora","China")
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        val pending_China = PendingIntent.getActivity(this,0,intent_HoraChina,PendingIntent.FLAG_UPDATE_CURRENT)
        remoteView.setOnClickPendingIntent(R.id.button,pending_Peru)
        remoteView.setOnClickPendingIntent(R.id.button2,pending_China)

        return remoteView
    }

    private fun generateNotification(title: String, message:String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.notification_channel_id)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        var notificationBuilder :NotificationCompat.Builder= NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.mipmap.fmc_notification_icon)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        notificationBuilder = notificationBuilder.setCustomContentView(getRemoteView(title,message))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                getString(R.string.notification_channel_id),
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 , notificationBuilder.build())
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")

    }

}