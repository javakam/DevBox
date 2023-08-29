package com.ando.devbox

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock.sleep
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * @author javakam
 * @date 2023-08-29 15:39:12
 */
class MainActivity : AppCompatActivity() {
    private var isMobileDebugMode = false // 判断手机是否处于开发者模式
    private val mTvTitle: TextView by lazy { findViewById(R.id.tvHello) }
    private val mBtAboutDevice: Button by lazy { findViewById(R.id.btAboutDevice) } // 关于手机
    private val mBtDeveloperOptions: Button by lazy { findViewById(R.id.btDeveloperOptions) }// 开发者选项/开发人员选项

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolBar: Toolbar = findViewById(R.id.toolbar)
        toolBar.setTitle("开发者选项")
        setSupportActionBar(toolBar)
    }

    override fun onResume() {
        super.onResume()
        sleep(50)
        checkDeveloperMode()
        setDisplay()
    }

    private fun checkDeveloperMode() {
        val developerMode = Settings.Global.getInt(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0)
        println("developerMode=$developerMode")
        isMobileDebugMode = (developerMode == 1)
        if (isMobileDebugMode) {
            // 开发者模式已开启
            mTvTitle.text = "开发者模式已开启！"
        } else {
            // 开发者模式未开启
            mTvTitle.text = "自上而下依次点击按钮即可！"
        }
    }

    private fun setDisplay() {
        if (isMobileDebugMode) {
            mBtAboutDevice.visibility = View.GONE
            mBtDeveloperOptions.visibility = View.VISIBLE
        } else {
            mBtAboutDevice.visibility = View.VISIBLE
            mBtDeveloperOptions.visibility = View.GONE
        }

        // 关于手机
        mBtAboutDevice.setOnClickListener {
            startActivity(Intent(Settings.ACTION_DEVICE_INFO_SETTINGS))
        }

        // 开发者选项/开发人员选项
        mBtDeveloperOptions.setOnClickListener {
            startActivity(Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS))
        }
    }
}