package cn.roy.relearn.android.lifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cn.roy.relearn.android.R

/**
 * @Description
 * @Author kk20
 * @Date 2020/11/9
 * @Version V1.0.0
 */
class AActivity : AppCompatActivity() {
    companion object {
        val TAG = "AActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate()")

        setContentView(R.layout.activity_a)

        findViewById<Button>(R.id.button).setOnClickListener{
            var intent = Intent(this,BActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.button2).setOnClickListener{
            var intent = Intent(this,CActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.button3).setOnClickListener{
            var intent = Intent(this,DActivity::class.java)
            startActivity(intent)
        }
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
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    log("监听到点击事件")
                    return true
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun log(log: String) {
        Log.d(TAG, log)
    }
}