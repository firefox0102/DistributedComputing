

//MAP FOR LOOKUO
var map = {
	video1: 9000,
	VidLeft: 1337,
	VidRight: 9002
}


//NODE REQUIRED MIDDLEWARE
var http = require('http'),
	fs = require('fs'),
    util = require('util');


var options;


//CREATS A PORT OF THE ROUTE TO RUN ON
http.createServer(function (req, res) {
 
var vidName = req.url;
vidName = vidName.slice(1);

var url = "127.0.0.1";

 options = {
  host: url,
  port: map[vidName],
  path: ""
};

console.log(options);
//SEND REQUEST TO THE SERVER WITH THE VIDEO 
var temp = http.get(options, function(response) {
  console.log('STATUS: ' + response.statusCode);
  console.log('HEADERS: ' + JSON.stringify(response.headers));

  // Buffer the body entirely for processing as a whole.
  // var bodyChunks = [];
  // response.on('data', function(chunk) {
  //   // You can process streamed parts here...
  //   bodyChunks.push(chunk);
  // }).on('end', function() {
  //   var body = Buffer.concat(bodyChunks);
  //   console.log('BODY: ' + body);
  //   // ...and/or process the entire body here.
  // })
	response.pipe(res);

});

temp.on('error', function(e) {
  console.log('ERROR: ' + e);
});

}).listen(3000, '127.0.0.1')
















