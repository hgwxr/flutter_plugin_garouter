import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MuiltListPage extends StatelessWidget {
  static const String TAG = "MuiltListPage";

  var list = new List<ListItem>.generate(
    1000,
    (i) => i % 6 == 0
        ? new HeadingItem("Heading $i")
        : new MessageItem("Sender $i", "Message body $i"),
  );

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: AppBar(
        title: Text("MuiltListPage"),
      ),
      body: new ListView.builder(
          itemCount: list.length,
          // ignore: missing_return
          itemBuilder: (context, index) {
            final item = list[index];
            // ignore: missing_return
            if (item is HeadingItem) {
              return new ListTile(
                title: new Text(
                  item.heading,
                  style: Theme.of(context).textTheme.headline,
                ),
              );
            } else if (item is MessageItem) {
              return new ListTile(
                title: new Text(item.sender),
                subtitle: new Text(item.body),
              );
            }
          }),
    );
  }
}

abstract class ListItem {}

class HeadingItem implements ListItem {
  final String heading;

  HeadingItem(this.heading);
}

class MessageItem implements ListItem {
  final String sender;
  final String body;

  MessageItem(this.sender, this.body);
}
