package com.dmall.flutter_plugin_garouter.flutterpage

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.accessibility.AccessibilityManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.dmall.gabridge.page.Page
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.renderer.FlutterRenderer
import io.flutter.view.AccessibilityBridge


class GAFlutterPage @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null
) : Page(context, attrs), Application.ActivityLifecycleCallbacks {
    private var mFlutterView: FlutterView
    private var availableEngine: FlutterEngine
    private var pageRoute = ""

    init {
//        View.inflate(context, R.layout.gaflutterpage, this)
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
        mFlutterView = FlutterView(context, attrs)
        availableEngine = FlutterEngineCacheWrapper.getAvailableEngine("main")
        mFlutterView.attachToFlutterEngine(availableEngine)
        addView(mFlutterView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
//        availableEngine.lifecycleChannel.appIsResumed()
        availableEngine.lifecycleChannel.appIsResumed()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun onPageInit() {
        super.onPageInit()
//        if ("/" == pageRoute) {
//            availableEngine.navigationChannel.setInitialRoute(pageRoute)
//        }else{
//        }
        val fragmentActivity = context as FragmentActivity
        fragmentActivity.registerActivityLifecycleCallbacks(this)
        availableEngine.navigationChannel.pushRoute(pageRoute)
//        availableEngine.destroy()
        availableEngine.lifecycleChannel.appIsResumed()
//        availableEngine?.lifecycleChannel?.appIsResumed()
    }

    override fun onPageWillBeHidden() {
        super.onPageWillBeHidden()
    }

    override fun onPageDidHidden() {
        super.onPageDidHidden()

    }

    override fun onPageDidBackwardFromMe() {
        super.onPageDidBackwardFromMe()
//        reattachToFlutterEngine()
    }

    override fun onPageDestroy() {
        super.onPageDestroy()
        mFlutterView.detachFromFlutterEngine()
        availableEngine?.lifecycleChannel?.appIsDetached();
        val fragmentActivity = context as FragmentActivity
        fragmentActivity.unregisterActivityLifecycleCallbacks(this)
    }

    override fun onPageDidShown() {
        super.onPageDidShown()
//        reattachToFlutterEngine()
    }
    override fun onActivityPaused(activity: Activity) {
//        mFlutterView.attachToFlutterEngine(availableEngine)
        availableEngine?.lifecycleChannel?.appIsPaused()
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
        availableEngine.lifecycleChannel?.appIsInactive()

    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityResumed(activity: Activity) {
        availableEngine.lifecycleChannel.appIsResumed()
        reattachToFlutterEngine()
    }


    fun reattachToFlutterEngine() {
        val flutterEngine=availableEngine
        // Instruct our FlutterRenderer that we are now its designated RenderSurface.
        val flutterRenderer: FlutterRenderer =  flutterEngine.getRenderer()
//       val isFlutterUiDisplayed = flutterRenderer.isDisplayingFlutterUi
//        renderSurface.attachToRenderer(flutterRenderer)
//        flutterRenderer.addIsDisplayingFlutterUiListener(flutterUiDisplayListener)

        // Initialize various components that know how to process Android View I/O
        // in a way that Flutter understands.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            mouseCursorPlugin = MouseCursorPlugin(this, this.flutterEngine.getMouseCursorChannel())
//        }
//        textInputPlugin = TextInputPlugin(
//                this,
//                this.flutterEngine.getTextInputChannel(),
//                this.flutterEngine.getPlatformViewsController())
//        localizationPlugin =  flutterEngine.getLocalizationPlugin()
//        androidKeyProcessor = AndroidKeyProcessor(this, this.flutterEngine.getKeyEventChannel(), textInputPlugin)
//        androidTouchProcessor = AndroidTouchProcessor(this.flutterEngine.getRenderer(),  /*trackMotionEvents=*/false)
       val accessibilityBridge = AccessibilityBridge(
                this,
                flutterEngine.getAccessibilityChannel(),
                (context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager),
                context.contentResolver,
                flutterEngine.getPlatformViewsController())
//        accessibilityBridge.setOnAccessibilityChangeListener(onAccessibilityChangeListener)
        setWillNotDraw(false)

        // Connect AccessibilityBridge to the PlatformViewsController within the FlutterEngine.
        // This allows platform Views to hook into Flutter's overall accessibility system.
        flutterEngine.getPlatformViewsController().attachAccessibilityBridge(accessibilityBridge)
        //        this.flutterEngine
//                .getPlatformViewsController()
//                .attachToFlutterRenderer(this.flutterEngine.getRenderer());

        // Inform the Android framework that it should retrieve a new InputConnection
        // now that an engine is attached.
        // TODO(mattcarroll): once this is proven to work, move this line ot TextInputPlugin
//        textInputPlugin.getInputMethodManager().restartInput(this)

        // Push View and Context related information from Android to Flutter.
//        sendUserSettingsToFlutter();
//        localizationPlugin.sendLocalesToFlutter(getResources().getConfiguration());
//        sendViewportMetricsToFlutter();
        flutterEngine.platformViewsController.attachToView(mFlutterView)

        // Notify engine attachment listeners of the attachment.
//        for (listener in flutterEngineAttachmentListeners) {
//            listener.onFlutterEngineAttachedToFlutterView(flutterEngine)
//        }

        // If the first frame has already been rendered, notify all first frame listeners.
        // Do this after all other initialization so that listeners don't inadvertently interact
        // with a ThrioFlutterView that is only partially attached to a FlutterEngine.
//        if (isFlutterUiDisplayed) {
//            flutterUiDisplayListener.onFlutterUiDisplayed()
//        }
    }
}