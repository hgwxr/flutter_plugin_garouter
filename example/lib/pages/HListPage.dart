import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class HListPage extends StatelessWidget {
  static const String TAG = "HListPage";

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: AppBar(
        title: Text("HListPage"),
      ),
      body: Padding(
        padding: EdgeInsets.all(18),
        child: new ListView(
          scrollDirection: Axis.horizontal,
          children: [
            new Container(
              width: 160.0,
              color: Colors.red,
            ),
            new Container(
              width: 160.0,
              color: Colors.blue,
            ),
            new Container(
              width: 160.0,
              color: Colors.green,
            ),
            new Container(
              width: 160.0,
              color: Colors.yellow,
            ),
            new Container(
              width: 160.0,
              color: Colors.orange,
            ),
          ],
        ),
      ),
    );
  }
}
