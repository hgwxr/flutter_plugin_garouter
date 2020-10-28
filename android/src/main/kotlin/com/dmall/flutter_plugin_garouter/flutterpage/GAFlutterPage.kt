package com.dmall.flutter_plugin_garouter.flutterpage

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.dmall.flutter_plugin_garouter.R
import com.dmall.gabridge.page.Page
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine


class GAFlutterPage @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null
) : Page(context, attrs) {
    private var mFlutterView: FlutterView
    private var availableEngine: FlutterEngine
    private var pageRoute = ""

    init {
//        View.inflate(context, R.layout.gaflutterpage, this)
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
        mFlutterView = findViewById(R.id.flutterViewContainer)
        availableEngine = FlutterEngineCacheWrapper.getAvailableEngine()
//        availableEngine.lifecycleChannel.appIsResumed()
        availableEngine.lifecycleChannel.appIsResumed()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun onPageInit() {
        super.onPageInit()
        if ("TestFlutterPageActivity" == pageRoute) {
            pageRoute = "/"
        }
//        availableEngine.destroy()
        mFlutterView.attachToFlutterEngine(availableEngine)
        availableEngine.lifecycleChannel.appIsResumed()
        availableEngine.navigationChannel.pushRoute(pageRoute)
//        availableEngine?.lifecycleChannel?.appIsResumed()
    }

    override fun onPageWillBeHidden() {
        super.onPageWillBeHidden()
        availableEngine.lifecycleChannel?.appIsInactive()
    }

    override fun onPageDidHidden() {
        super.onPageDidHidden()
        availableEngine?.lifecycleChannel?.appIsPaused()
    }

    override fun onPageDestroy() {
        super.onPageDestroy()
        availableEngine?.lifecycleChannel?.appIsDetached();
        mFlutterView.detachFromFlutterEngine()
    }


}