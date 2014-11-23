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
var url = "127.0.0.1";

//CREATS A PORT OF THE ROUTE TO RUN ON
http.createServer(function (req, res) {
   res.writeHead(200, {"Content-Type": "application/json"});
  startlookTime = new Date();
 console.log(req);
 console.log(req.url + "is req.url");
  var vidName = req.url;
  console.log(vidName + "is vifname");

  vidName = vidName.slice(1);
  console.log(vidName + " is vifname after");



  var obj = {vidName: vidName, port: map[vidName]}
  res.write(JSON.stringify(obj));
  console.log(res)
res.end();
  

  
  endlooTime = new Date();

  console.log("look tim is: " + timeDif(startlookTime, endlooTime));
  

  	

 


  // temp.on('finish', function(){
  //   endTranTime = new Date();
  //   var dif = timeDif(startTranTime, endTranTime)
  //   console.log("the dif is: " + dif);

  // });



}).listen(3001, url)
















