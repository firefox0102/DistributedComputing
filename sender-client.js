/*
 * Inspired by: http://stackoverflow.com/questions/4360060/video-streaming-with-html-5-via-node-js
 */
 
var http = require('http'),
    fs = require('fs'),
    util = require('util'),
    ip = '127.0.0.1';
 
http.createServer(function (req, res) {
  var path = 'vids/badfeeling.wav';
  var stat = fs.statSync(path);
  var total = stat.size;

    console.log('ALL: ' + total);
    res.writeHead(200, { 'Content-Length': total, 'Content-Type': 'video/mp4' });
    fs.createReadStream(path).pipe(res);
  
}).listen(9001, ip);

http.createServer(function (req, res) {
  var path = 'vids/leftvid.mp4';
  var stat = fs.statSync(path);
  var total = stat.size;
  
    console.log('ALL: ' + total);
    res.writeHead(200, { 'Content-Length': total, 'Content-Type': 'video/mp4' });
    fs.createReadStream(path).pipe(res);
  
}).listen(9002, ip);

http.createServer(function (req, res) {
  var path = 'vids/vidleft.mp4';
  var stat = fs.statSync(path);
  var total = stat.size;

    console.log('ALL: ' + total);
    res.writeHead(200, { 'Content-Length': total, 'Content-Type': 'video/mp4' });
    fs.createReadStream(path).pipe(res);
  
}).listen(9003, ip);

http.createServer(function (req, res) {
  var path = 'vids/welcomehome.wav';
  var stat = fs.statSync(path);
  var total = stat.size;

    console.log('ALL: ' + total);
    res.writeHead(200, { 'Content-Length': total, 'Content-Type': 'video/mp4' });
    fs.createReadStream(path).pipe(res);
  
}).listen(9004, ip);

console.log('client-server running at...http://'+ip);