import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class LongListPage extends StatelessWidget {
  static const String TAG = "LongListPage";

  @override
  Widget build(BuildContext context) {
    var list = new List<String>.generate(10000, (index) => "Item $index");
    var widgets = list.map((item) => new ListTile(
          title: new Text('$item'),
        ));
    var rWidgets = ListTile.divideTiles(
        context: context, tiles: widgets, color: Colors.black).toList();
    return new Scaffold(
      appBar: AppBar(
        title: Text("LongListPage"),
      ),
      body: Padding(
        padding: EdgeInsets.all(18),
        child: new ListView.builder(
            itemCount: list.length,
            itemBuilder: (context, index) {
              return rWidgets[index];
            }),
      ),
    );
  }
}
