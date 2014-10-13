//MAP FOR LOOKUO
var map = {
	badfeeling: 9001,
	gamelab: 9002,
  leftvid: 9003,
  welcomehome: 9004,
  stupid: 9004
}
var timeDif = function(start, end){
  return end - start;
}

//NODE REQUIRED MIDDLEWARE
var http = require('http'),
	  fs = require('fs'),
    util = require('util');


var options;
var endTranTime;
var startTranTime;
var endlooTime;
var startlookTime;
var url = "192.168.1.7";

//CREATS A PORT OF THE ROUTE TO RUN ON
http.createServer(function (req, res) {
  startlookTime = new Date();
 
  var vidName = req.url;
  vidName = vidName.slice(1);

  

  options = {
    host: url,
    port: map[vidName],
    path: ""
  };
  endlooTime = new Date();

  //console.log("look tim is: " + timeDif(startlookTime, endlooTime));
  //console.log(options);

  //SEND REQUEST TO THE SERVER WITH THE VIDEO 
  var temp = http.get(options, function(response) {
    startTranTime = new Date();
    
  	response.pipe(res);

  });
  res.on('finish', function(){
     endTranTime = new Date();
    var dif = timeDif(startTranTime, endTranTime)
    console.log(dif + ",");

  });

  temp.on('error', function(e) {
    console.log('ERROR: ' + e);
  });

}).listen(3000, url)
















