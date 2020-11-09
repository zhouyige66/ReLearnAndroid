package cn.roy.relearn.android.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * @Description
 * @Author kk20
 * @Date 2020/11/9
 * @Version V1.0.0
 */
class AActivity : AppCompatActivity() {
    companion object {
        val TAG = "AActivity"
        val LOAN_TITLE = "loanTitle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate()")
    }

    override fun onRestart() {
        super.onRestart()
        log("onRestart()")
    }

    override fun onStart() {
        super.onStart()
        log("onStart()")
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

    private fun log(log: String) {
        Log.d(TAG, log)
    }
}