import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_plugin_garouter/flutter_plugin_garouter.dart';
import 'package:flutter_plugin_garouter_example/pages/HListPage.dart';
import 'package:flutter_plugin_garouter_example/pages/LongListPage.dart';
import 'package:flutter_plugin_garouter_example/pages/MuiltListPage.dart';
import 'package:flutter_plugin_garouter_example/pages/PlamVersion.dart';
import 'package:flutter_plugin_garouter_example/pages/image/ImagePage.dart';

void main() {
  runZonedGuarded(() {
    runApp(MyApp());
  }, (e, s) => print("================>" + e + "  s:" + s.toString()));
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      routes: {
        "/": (context) => MainPage(),
        HListPage.TAG: (context) => HListPage(),
        LongListPage.TAG: (context) => LongListPage(),
        MuiltListPage.TAG: (context) => MuiltListPage(),
        ImagePage.TAG: (context) => ImagePage(),
        PlamVersion.TAG: (context) => PlamVersion(),
      },
    );
  }
}

class MainPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _MyAppState();
  }
}

class _MyAppState extends State<MainPage> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await FlutterPluginGarouter.platformVersion;
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: new ListView(
          children: <Widget>[
            new ListTile(
              leading: new Icon(Icons.map),
              title: new Text('打开测试activity122'),
              onTap: () async => {
                await FlutterPluginGarouter.forward("TestFlutterPageActivity",
                    arguments: {
                      "name": "---",
                      "data": [1, 2, 3, 4]
                    })
              },
              onLongPress: () => {("====>onLongPress  Map")},
            ),
            new ListTile(
              leading: new Icon(Icons.photo_album),
              title: new Text('打开测试FlutterPage'),
              onTap: () async => {await FlutterPluginGarouter.forward("/")},
            ),
            new ListTile(
              leading: new Icon(Icons.photo_album),
              title: new Text('打开测试' + PlamVersion.TAG),
              onTap: () async =>
                  {await FlutterPluginGarouter.forward(PlamVersion.TAG)},
            ),
            new ListTile(
              leading: new Icon(Icons.photo_album),
              title: new Text('打开测试' + MuiltListPage.TAG),
              onTap: () async =>
                  {await FlutterPluginGarouter.forward(MuiltListPage.TAG)},
            ),
            new ListTile(
              leading: new Icon(Icons.photo_album),
              title: new Text('打开测试' + HListPage.TAG),
              onTap: () async =>
                  {await FlutterPluginGarouter.forward(HListPage.TAG)},
            ),
            new ListTile(
                leading: new Icon(Icons.photo_album),
                title: new Text('打开测试' + ImagePage.TAG),
                onTap: () async => {
                      // Navigator.pushNamed(context, ImagePage.TAG)
                      await FlutterPluginGarouter.forward(ImagePage.TAG)
                    }),
            new ListTile(
                leading: new Icon(Icons.photo_album),
                title: new Text('打开测试' + ImagePage.TAG),
                onTap: () async => {
                      // Navigator.pushNamed(context, ImagePage.TAG)
                      await FlutterPluginGarouter.forward(ImagePage.TAG)
                    }),
            new ListTile(
                leading: new Icon(Icons.photo_album),
                title: new Text('打开测试' + ImagePage.TAG),
                onTap: () async => {
                      // Navigator.pushNamed(context, ImagePage.TAG)
                      await FlutterPluginGarouter.forward(ImagePage.TAG)
                    }),
            new ListTile(
                leading: new Icon(Icons.photo_album),
                title: new Text('打开测试' + ImagePage.TAG),
                onTap: () async => {
                      // Navigator.pushNamed(context, ImagePage.TAG)
                      await FlutterPluginGarouter.forward(ImagePage.TAG)
                    }),
            new ListTile(
                leading: new Icon(Icons.photo_album),
                title: new Text('打开测试' + ImagePage.TAG),
                onTap: () async => {
                      // Navigator.pushNamed(context, ImagePage.TAG)
                      await FlutterPluginGarouter.forward(ImagePage.TAG)
                    }),
            new ListTile(
                leading: new Icon(Icons.photo_album),
                title: new Text('打开测试' + ImagePage.TAG),
                onTap: () async => {
                      Navigator.pushNamed(context, ImagePage.TAG),
                      await FlutterPluginGarouter.forward(ImagePage.TAG)
                    }),
            new ListTile(
                leading: new Icon(Icons.photo_album),
                title: new Text('打开测试' + ImagePage.TAG),
                onTap: () async => {
                      // Navigator.pushNamed(context, ImagePage.TAG)
                      await FlutterPluginGarouter.forward(ImagePage.TAG)
                    }),
            new ListTile(
                leading: new Icon(Icons.photo_album),
                title: new Text('打开测试' + ImagePage.TAG),
                onTap: () async => {
                      // Navigator.pushNamed(context, ImagePage.TAG)
                      await FlutterPluginGarouter.forward(ImagePage.TAG)
                    }),
            new ListTile(
                leading: new Icon(Icons.photo_album),
                title: new Text('打开测试' + ImagePage.TAG),
                onTap: () async => {
                      // Navigator.pushNamed(context, ImagePage.TAG)
                      await FlutterPluginGarouter.forward(ImagePage.TAG)
                    }),
            new ListTile(
                leading: new Icon(Icons.photo_album),
                title: new Text('打开测试' + ImagePage.TAG),
                onTap: () async => {
                      // Navigator.pushNamed(context, ImagePage.TAG)
                      await FlutterPluginGarouter.forward(ImagePage.TAG)
                    }),
          ],
        ),
      ),
    );
  }
}
