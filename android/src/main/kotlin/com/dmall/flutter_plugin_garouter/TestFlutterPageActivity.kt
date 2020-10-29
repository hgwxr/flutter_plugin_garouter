package com.dmall.flutter_plugin_garouter


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.dmall.flutter_plugin_garouter.flutterpage.FlutterEngineCacheWrapper
import com.dmall.garouter.navigator.GANavigator
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor


class TestFlutterPageActivity : FragmentActivity() {
    companion object {
        // Define a tag String to represent the FlutterFragment within this
        // Activity's FragmentManager. This value can be whatever you'd like.
        private const val TAG_FLUTTER_FRAGMENT = "flutter_fragment"
    }

    // Declare a local variable to reference the FlutterFragment so that you
    // can forward calls to it later.
    private var flutterFragment: FlutterFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate a layout that has a container for your FlutterFragment. For
        // this example, assume that a FrameLayout exists with an ID of
        // R.id.fragment_container.
        setContentView(R.layout.app_main_layout)

        val gaNavigator = findViewById<GANavigator>(R.id.gaNavigator)
        gaNavigator?.forward("app://GAFlutterPage?pageRoute=/")
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.app_main_layout)
//        val gaNavigator = findViewById<GANavigator>(R.id.gaNavigator)
////        gaNavigator?.let {
////            it.forward("app://GAFlutterPage")
////        }
//            val beginTransaction = supportFragmentManager.beginTransaction()
//            beginTransaction.add(FlutterFragment.withNewEngine().build(),"/").commitAllowingStateLoss()
////        gaNavigator.postDelayed({
////            GANavigator.getInstance().forward("app://GAFlutterPage")
////        },3000)
//    }
//
    override fun onBackPressed() {

        GANavigator.getInstance().let {
            if (it.getTopPage(1) != null) {
                FlutterEngineCacheWrapper.getAvailableEngine("main").navigationChannel.popRoute()
                it.backward()
            } else {
                super.onBackPressed()
            }
        }
    }
}
