package com.artsam.selfmaker.utils.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.artsam.selfmaker.MainActivity


class SplashActivity : AppCompatActivity() {
        /** Duration of wait **/
    private val SPLASH_DISPLAY_LENGTH: Int = 1000
    // 4. Declare the Handler as a member variable
    private var mHandler: Handler? = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        mHandler?.postDelayed({ /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this, MainActivity::class.java)
            this.startActivity(mainIntent)
            this.finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }


    // 6. Override onDestroy()
    override fun onDestroy() {
        // 7. Remove any delayed Runnable(s) and prevent them from executing.
        mHandler?.removeCallbacksAndMessages(null)

        // 8. Eagerly clear mHandler allocated memory
        mHandler = null
        super.onDestroy()
    }
}