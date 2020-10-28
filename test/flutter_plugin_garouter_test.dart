import 'dart:developer' as developer;

import 'package:flutter/services.dart';
import 'package:flutter_plugin_garouter/flutter_plugin_garouter.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_plugin_garouter');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });
  test('getPlatformVersion', () async {
    developer.log('log me', name: 'my.app.category');
    expect(await FlutterPluginGarouter.platformVersion, '42');
  });
}
