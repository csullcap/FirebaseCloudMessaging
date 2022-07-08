package danp.lab08.firebasecloudmessaging

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AparteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reloj_aparte)
        val noti : TextView = findViewById(R.id.response_notification)
        intent.extras?.getString("event")?.let {
            noti.setText(intent.extras?.getString("event"))
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }
}