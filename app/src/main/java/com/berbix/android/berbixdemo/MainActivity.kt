package com.berbix.android.berbixdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.berbix.berbixverify.BerbixConfigurationBuilder
import com.berbix.berbixverify.BerbixConstants
import com.berbix.berbixverify.BerbixErrorReason
import com.berbix.berbixverify.BerbixResultListener
import com.berbix.berbixverify.BerbixResultStatus
import com.berbix.berbixverify.BerbixSDK


class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val startVerificationButton = findViewById<Button>(R.id.start_verification_button)

    startVerificationButton.setOnClickListener {
      val sdk = BerbixSDK()
      val config = BerbixConfigurationBuilder()
          .setClientToken("your_token_here") // fetch token from backend or paste in for demo
          .build()
      sdk.startFlow(this, config)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == BerbixConstants.REQUEST_CODE_BERBIX_FLOW) {
      BerbixResultStatus.handleResult(resultCode, object : BerbixResultListener{

        override fun onComplete() {
          Toast.makeText(this@MainActivity, "flow completed", Toast.LENGTH_LONG).show()
        }

        override fun onFailure(status: BerbixErrorReason) {
          when (status) {
            BerbixErrorReason.USER_EXIT -> Toast.makeText(this@MainActivity, "user exited flow", Toast.LENGTH_LONG).show()
            BerbixErrorReason.NO_CAMERA_PERMISSIONS -> Toast.makeText(this@MainActivity, "no camera permission", Toast.LENGTH_LONG).show()
            BerbixErrorReason.UNABLE_TO_LAUNCH_CAMERA -> Toast.makeText(this@MainActivity, "could not launch camera", Toast.LENGTH_LONG).show()
            BerbixErrorReason.BERBIX_ERROR -> Toast.makeText(this@MainActivity, "Berbix error occurred", Toast.LENGTH_LONG).show()
            BerbixErrorReason.UNKNOWN_ERROR -> Toast.makeText(this@MainActivity, "unknown error received", Toast.LENGTH_LONG).show()
            else -> Toast.makeText(this@MainActivity, "unknown error received", Toast.LENGTH_LONG).show()
          }
        }
      })
    }
  }
}
