package com.ando.devbox

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock.sleep
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.Toolbar

/**
 * @author javakam
 * @date 2023-08-29 15:39:12
 */
class MainActivity : AppCompatActivity() {
    private var isMobileDeveloperMode = false // 判断手机是否处于开发者模式
    private var isMobileUsbDebuggingMode = false // 判断手机是否处于USB调试模式
    private val mRadioUsbMode: AppCompatRadioButton by lazy { findViewById(R.id.usbDebuggingRadioButton) }
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
        refreshDeveloperMode()
        refreshUsbDebugging()
        setDisplay()
    }

    private fun refreshDeveloperMode() {
        isMobileDeveloperMode = (getDeveloperModeCode() == 1)
        println("开发者模式 = $isMobileDeveloperMode")
        if (isMobileDeveloperMode) {
            // 开发者模式已开启
            mTvTitle.text = "开发者模式已开启！"
        } else {
            // 开发者模式未开启
            mTvTitle.text = "连续点击版本号直到提示《您已进入开发者模式》！"
        }
    }

    private fun refreshUsbDebugging() {
        isMobileUsbDebuggingMode = (getUsbDebuggingCode() == 1)
        println("USB调试模式 = $isMobileUsbDebuggingMode")
        mRadioUsbMode.isChecked = isMobileUsbDebuggingMode
        if (isMobileUsbDebuggingMode) {
            mRadioUsbMode.text = "USB调试已开启"
        } else {
            mRadioUsbMode.text = "USB调试已关闭"
        }
    }

    //获取开发者模式状态值, 返回值0表示未开启，1表示已开启
    private fun getDeveloperModeCode(): Int = Settings.Global.getInt(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0)

    //获取USB调试状态值, 返回值0表示未开启，1表示已开启
    private fun getUsbDebuggingCode(): Int = Settings.Secure.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0)

    private fun setDisplay() {
        if (isMobileDeveloperMode) {
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