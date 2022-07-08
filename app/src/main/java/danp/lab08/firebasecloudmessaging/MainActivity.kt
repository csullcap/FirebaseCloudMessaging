package danp.lab08.firebasecloudmessaging

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tokenText : EditText = findViewById(R.id.token)


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            tokenText.setText(token)
        })
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val noti : EditText = findViewById(R.id.response_notification)
        intent.extras?.getString("hora")?.let { Log.d(TAG, it) }
        noti.setText("PERU")
    }

}