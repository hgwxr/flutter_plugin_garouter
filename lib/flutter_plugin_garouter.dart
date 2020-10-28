import 'dart:async';

import 'package:flutter/services.dart';

class FlutterPluginGarouter {
  static const MethodChannel _channel =
      const MethodChannel('flutter_plugin_garouter');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<bool> forward(String pageUrl, {Map arguments}) async {
    // arguments['pageUrl'] = pageUrl;
    // final String version =
    //     await _channel.invokeMethod('getPlatformVersion', pageUrl);

    if (arguments != null) {
      arguments['pageRoute'] = pageUrl;
    } else {
      arguments = {
        'pageRoute': pageUrl,
      };
    }
    final bool success =
        await _channel.invokeMethod('forwardToPage', arguments);
    return success;
  }
}
