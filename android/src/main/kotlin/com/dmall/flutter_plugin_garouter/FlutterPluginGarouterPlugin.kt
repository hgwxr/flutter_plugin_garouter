package com.dmall.flutter_plugin_garouter

import android.content.Intent
import android.os.Build
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import com.dmall.flutter_plugin_garouter.flutterpage.FlutterEngineCacheWrapper
import com.dmall.garouter.navigator.GANavigator
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.FlutterFragmentActivity

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** FlutterPluginGarouterPlugin */
class FlutterPluginGarouterPlugin : FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_plugin_garouter")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } else if (call.method == "forwardToPage") {
//      result.success("Android ${android.os.Build.VERSION.RELEASE}")
            if (call.arguments is Map<*, *>) {
                val any = (call.arguments as Map<*, *>)["pageRoute"]
                if (any == "TestFlutterPageActivity") {
                    GAFlutterGallen.getCurrentActivity()?.let {
                        it.startActivity(Intent(it, TestFlutterPageActivity::class.java))
                    }
                    result.success(true)
                }else{
                    try {
                        var pageParams = mapOf<String, Any>()
                        val arguments = call.arguments
                        // TODO: 2020/10/27 类型强转  会出问题
                        pageParams = arguments as Map<String, Any>
                        var pageRoute = "/"
                        if (pageParams.containsKey("pageRoute")) {
                            pageRoute = pageParams["pageRoute"] as String
                        }
                        val activity = GAFlutterGallen.getCurrentActivity()
//                        activity?.let {
//                            val fragmentActivity = it as FragmentActivity
//                            val beginTransaction = fragmentActivity.supportFragmentManager.beginTransaction()
//                            beginTransaction.add(FlutterFragment.withNewEngine().initialRoute(pageRoute).build(), pageRoute).commitAllowingStateLoss()
//                        }
//                        activity?.startActivity(
//                                FlutterFragmentActivity.withNewEngine().initialRoute(pageRoute)
//                                        .build(activity))
//                        FlutterEngineCacheWrapper.getAvailableEngine().navigationChannel.pushRoute(pageRoute)

                        GANavigator.getInstance()?.forward("app://GAFlutterPage?pageRoute=$pageRoute", pageParams)
                        result.success(true)
                    } catch (e: Exception) {
                        result.error("0001", e.message, e.localizedMessage)
                    }
                }
            } else{
                result.notImplemented()
            }
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
