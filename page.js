var http = require('http');
var fs = require('fs');

http.createServer(function(request, response) {
  response.writeHead(200);

  fs.readFile('vids/LeftVid.mp4', function(err, contents) {
    response.write(contents);
    response.end();
  });

}).listen(8080);