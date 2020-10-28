package com.dmall.flutter_plugin_garouter_example

import android.app.Application
import com.dmall.flutter_plugin_garouter.FlutterPluginGarouterPlugin
import com.dmall.flutter_plugin_garouter.GAFlutterGallen
import com.dmall.flutter_plugin_garouter.flutterpage.GAFlutterPage
import com.dmall.garouter.navigator.GANavigator
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugins.GeneratedPluginRegistrant

class MApp : Application() {
    override fun onCreate() {
        super.onCreate()
        GANavigator.registAppPage(GAFlutterPage::class.java)
        GAFlutterGallen.initGAFlutter(this)
       val flutterEngine =  FlutterEngine(this);
        // Configure an initial route.
//        flutterEngine.getNavigationChannel().setInitialRoute("your/route/here");
        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );

        // 创建预加载的flutterEngine之后，在这里为证flutterEngine初始化用到的第三方flutter插件。
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        // Cache the FlutterEngine to be used by FlutterActivity or FlutterFragment.
        FlutterEngineCache
                .getInstance()
                .put("flutter_engine", flutterEngine);

        flutterEngine.plugins.add(FlutterPluginGarouterPlugin())
    }
}