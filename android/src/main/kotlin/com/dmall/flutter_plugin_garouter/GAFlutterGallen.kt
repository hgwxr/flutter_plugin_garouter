package com.dmall.flutter_plugin_garouter

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

object GAFlutterGallen {
    var sApplicaiton: Application? = null
    var activityStack = mutableListOf<Activity>()

    fun getCurrentActivity():Activity?{
      return  if (activityStack.size > 0) {
            activityStack[activityStack.lastIndex]
        } else {
            null
        }
    }
    fun initGAFlutter(application: Application) {
        sApplicaiton = application
        sApplicaiton?.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
                Log.e("TAG", "onActivityPaused() called with: activity = ${activity.javaClass.simpleName}")
            }

            override fun onActivityStarted(activity: Activity) {
                Log.e("TAG", "onActivityStarted() called with: activity = $activity.javaClass.simpleName")
            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.e("TAG", "onActivityDestroyed() called with: activity = $activity.javaClass.simpleName")
                activityStack.remove(activity)
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

                Log.e("TAG", "onActivitySaveInstanceState() called with: activity = $activity.javaClass.simpleName, outState = $outState")
            }

            override fun onActivityStopped(activity: Activity) {

                Log.e("TAG", "onActivityStopped() called with: activity = $activity.javaClass.simpleName")
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.e("TAG", "onActivityCreated() called with: activity = $activity.javaClass.simpleName, savedInstanceState = $savedInstanceState")
                activityStack.add(activity)
            }

            override fun onActivityResumed(activity: Activity) {
                Log.e("TAG", "onActivityResumed() called with: activity = $activity.javaClass.simpleName")

            }
        })
    }

}