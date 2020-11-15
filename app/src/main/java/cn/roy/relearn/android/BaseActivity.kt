package cn.roy.relearn.android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.roy.relearn.android.launchmodel.SingleInstanceActivity
import cn.roy.relearn.android.launchmodel.SingleTaskActivity
import cn.roy.relearn.android.launchmodel.SingleTopActivity
import cn.roy.relearn.android.launchmodel.StandardActivity
import cn.roy.relearn.android.lifecycle.AActivity
import cn.roy.relearn.android.lifecycle.BActivity
import cn.roy.relearn.android.lifecycle.CActivity
import cn.roy.relearn.android.lifecycle.DActivity
import cn.roy.relearn.android.service.TaskService

/**
 * @Description
 * @Author kk20
 * @Date 2020/11/9
 * @Version V1.0.0
 */
open class BaseActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        log("onCreate()")
        findViewById<TextView>(R.id.tv_activity_name).text = this.toString()
        findViewById<Button>(R.id.button).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
        findViewById<Button>(R.id.button6).setOnClickListener(this)
        findViewById<Button>(R.id.button7).setOnClickListener(this)
        findViewById<Button>(R.id.button8).setOnClickListener(this)

        findViewById<Button>(R.id.button9).setOnClickListener(this)
        findViewById<Button>(R.id.button10).setOnClickListener(this)
        findViewById<Button>(R.id.button11).setOnClickListener(this)
        findViewById<Button>(R.id.button12).setOnClickListener(this)
    }

    override fun onRestart() {
        super.onRestart()
        log("onRestart()")
    }

    override fun onStart() {
        super.onStart()
        log("onStart()")
    }

    override fun onResume() {
        super.onResume()
        log("onResume()")
    }

    override fun onPause() {
        super.onPause()
        log("onPause()")
    }

    override fun onStop() {
        super.onStop()
        log("onStop()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        log("onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        log("onRestoreInstanceState()")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy()")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    log("监听到点击事件")
                    return true
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onClick(v: View) {
        var intent = Intent()
        when (v.id) {
            R.id.button -> {
                intent.setClass(this, BActivity::class.java)
                startActivity(intent)
            }
            R.id.button2 -> {
                intent.setClass(this, CActivity::class.java)
                startActivity(intent)
            }
            R.id.button3 -> {
                intent.setClass(this, DActivity::class.java)
                startActivity(intent)
            }
            R.id.button4 -> {
                intent.setClass(this, StandardActivity::class.java)
                startActivity(intent)
            }
            R.id.button5 -> {
                intent.setClass(this, SingleTopActivity::class.java)
                startActivity(intent)
            }
            R.id.button6 -> {
                intent.setClass(this, SingleTaskActivity::class.java)
                startActivity(intent)
            }
            R.id.button7 -> {
                intent.setClass(this, SingleInstanceActivity::class.java)
                startActivity(intent)
            }
            R.id.button8 -> {
                intent.setClass(this, AActivity::class.java)
                startActivity(intent)
            }

            R.id.button9 -> {
                intent.setClass(this, TaskService::class.java)
                intent.putExtra("flag", 0)
                startService(intent)
            }
            R.id.button10 -> {
                intent.setClass(this, TaskService::class.java)
                intent.putExtra("flag", 1)
                startService(intent)
            }
            R.id.button11 -> {
                intent.setClass(this, TaskService::class.java)
                intent.putExtra("flag", 2)
                startService(intent)
            }
            R.id.button12 -> {
                intent.setClass(this, TaskService::class.java)
                intent.putExtra("flag", 3)
                startService(intent)
            }
        }
    }

    open fun log(log: String) {
        Log.d(this.javaClass.simpleName, log)
    }

}