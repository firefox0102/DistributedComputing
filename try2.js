var express = require('express');
var app = express();
var url = require('url');
var http = require('http');
require('json-response');
var U = "127.0.0.1"

app.get('/', function(req, res){
 
  var url_parts = url.parse(req.url, true);
	var query = url_parts.query;
	
	var vidName = query.name;

  options = {
    host: U,
    port: 3000,
    path: "/?name="+vidName,
    method: "GET"
  };

  console.log(options);

  // //SEND REQUEST TO THE SERVER WITH THE VIDEO 
  var temp = http.get(options, function(response) {
    startTranTime = new Date();
    
    //console.log(response);
   response.on('data', function(data){
   	var chunk = data.toString('utf8');
   	console.log(chunk);
   	//var obj = JSON.parse(chunk);

   	res.send(chunk);

   });
   
  	// res.write(""+response);
   //  res.end();

  });


  temp.on('error', function(e) {
    console.log('ERROR: ' + e);
  });


});

app.listen(3001);
















