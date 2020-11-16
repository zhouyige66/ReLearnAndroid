package cn.roy.relearn.android.service

import android.app.Service
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.IBinder
import android.util.Log
import cn.roy.relearn.android.launchmodel.SingleInstanceActivity
import cn.roy.relearn.android.launchmodel.SingleTaskActivity
import cn.roy.relearn.android.launchmodel.SingleTopActivity
import cn.roy.relearn.android.launchmodel.StandardActivity
import cn.roy.relearn.android.lifecycle.AActivity

/**
 * @Description:
 * @Author: zhouzongyi@cpic.com.cn
 * @Date: 11/15/20 8:49 PM
 * @Version: v1.0
 */
class TaskService : Service() {

    override fun onCreate() {
        super.onCreate()
        log("onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand()")
        var activityIntent = Intent()
        var flag = intent?.getIntExtra("flag", 0)
        when (flag) {
            1 -> {
                activityIntent.setClass(this, StandardActivity::class.java)
                activityIntent.flags = FLAG_ACTIVITY_NEW_TASK
                startActivity(activityIntent)
            }
            2 -> {
                activityIntent.setClass(this, StandardActivity::class.java)
                activityIntent.flags = FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(activityIntent)
            }
            3 -> {
                activityIntent.setClass(this, StandardActivity::class.java)
                activityIntent.flags = FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(activityIntent)
            }
            4 -> {
                activityIntent.setClass(this, StandardActivity::class.java)
                activityIntent.flags = FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(activityIntent)
            }
            5 -> {
                activityIntent.setClass(this, SingleTopActivity::class.java)
                activityIntent.flags = FLAG_ACTIVITY_NEW_TASK
                startActivity(activityIntent)
            }
            6 -> {
                activityIntent.setClass(this, SingleTopActivity::class.java)
                activityIntent.flags = FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(activityIntent)
            }
            7 -> {
                activityIntent.setClass(this, SingleTopActivity::class.java)
                activityIntent.flags = FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(activityIntent)
            }
            8 -> {
                activityIntent.setClass(this, SingleTopActivity::class.java)
                activityIntent.flags = FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(activityIntent)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        log("onDestroy()")
        super.onDestroy()
    }

    override fun onLowMemory() {
        log("onLowMemory()")
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        log("onTrimMemory()")
        super.onTrimMemory(level)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
        log("onBind()")
    }

    fun log(log: String) {
        Log.d(this.javaClass.simpleName, log)
    }
}