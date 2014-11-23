

//MAP FOR LOOKUO

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
  startlookTime = new Date();
 
  var vidName = req.url;
  vidName = vidName.slice(1);

  

  options = {
    host: url,
    port: 3000,
    path: "/?name="+vidName,
    method: "GET"
  };
  endlooTime = new Date();

  console.log("look tim is: " + timeDif(startlookTime, endlooTime));
  console.log(options);

  //SEND REQUEST TO THE SERVER WITH THE VIDEO 
  var temp = http.get(options, function(response) {
    startTranTime = new Date();
    
    console.log(response.body);


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
  	res.write(""+response);
    res.end();

  });
  res.on('finish', function(){
     endTranTime = new Date();
    var dif = timeDif(startTranTime, endTranTime)
    console.log("the dif is: " + dif);
  });

  // temp.on('finish', function(){
  //   endTranTime = new Date();
  //   var dif = timeDif(startTranTime, endTranTime)
  //   console.log("the dif is: " + dif);

  // });

  temp.on('error', function(e) {
    console.log('ERROR: ' + e);
  });

}).listen(3001, url)
















