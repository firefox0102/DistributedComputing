var express = require('express');
var app = express();
var url = require('url');

require('json-response');

var map = {
	badfeeling: 9001,
	gamelab: 9002,
  leftvid: 9003,
  welcomehome: 9004,
  stupid: 9004
}

app.get('/', function(req, res){
	
	var url_parts = url.parse(req.url, true);
	var query = url_parts.query;
	console.log(query);
	var port = map[query.name];
	var obj = port;
  res.send(JSON.stringify(obj));
});

app.listen(3000);