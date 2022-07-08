package danp.lab08.firebasecloudmessaging

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText

class RelojAparte : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reloj_aparte)
        val noti : EditText = findViewById(R.id.response_notification)

        if (intent.getExtras() != null) {
            intent.extras?.getString("hora")?.let { Log.d(ContentValues.TAG, it) }
            noti.setText("PERU")
        }

    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }
}