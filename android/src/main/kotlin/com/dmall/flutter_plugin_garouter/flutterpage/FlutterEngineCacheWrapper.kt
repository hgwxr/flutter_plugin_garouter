package com.dmall.flutter_plugin_garouter.flutterpage

import com.dmall.flutter_plugin_garouter.FlutterPluginGarouterPlugin
import com.dmall.flutter_plugin_garouter.GAFlutterGallen
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterJNI
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.embedding.engine.loader.FlutterLoader


object FlutterEngineCacheWrapper {
    private fun getArgsFromIntent(): Array<String> {
        // Before adding more entries to this list, consider that arbitrary
        // Android applications can generate intents with extra data and that
        // there are many security-sensitive args in the binary.
        return arrayOf("--trace-startup", "--start-paused", "--enable-dart-profiling")
    }

    fun getAvailableEngine(engineKey: String = ""): FlutterEngine {
        var engineCache = FlutterEngineCache.getInstance().get(engineKey)
        if (engineCache == null) {
            val flutterLoader = FlutterLoader()
            val flutterJNI = FlutterJNI()
            val argsFromIntent = getArgsFromIntent()
            val sApplicaiton = GAFlutterGallen.sApplicaiton!!
            engineCache = FlutterEngine(sApplicaiton, argsFromIntent, true)
            engineCache.dartExecutor.executeDartEntrypoint(
                    DartExecutor.DartEntrypoint.createDefault()
            )
            FlutterEngineCache.getInstance().put(engineKey, engineCache)
            engineCache.getPlugins().add(FlutterPluginGarouterPlugin())
        }
        return engineCache
    }
}