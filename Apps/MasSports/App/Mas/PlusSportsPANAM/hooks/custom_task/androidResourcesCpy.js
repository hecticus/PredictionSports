#!/usr/bin/env node
'use strict';

var filestocopy = [{
    "res/android/drawable/notification_icon.png": 
    "platforms/android/res/drawable/notification_icon.png"
},{
    "res/android/drawable-hdpi/notification_icon.png": 
    "platforms/android/res/drawable-hdpi/notification_icon.png"
},{
    "res/android/drawable-ldpi/notification_icon.png": 
    "platforms/android/res/drawable-ldpi/notification_icon.png"
},{
    "res/android/drawable-mdpi/notification_icon.png": 
    "platforms/android/res/drawable-mdpi/notification_icon.png"
},{
    "res/android/drawable-xhdpi/notification_icon.png": 
    "platforms/android/res/drawable-xhdpi/notification_icon.png"
}];
 
var fs = require('fs');
var path = require('path');
console.log('### androidResourcesCpy'); 
  
filestocopy.forEach(function(obj) {
    Object.keys(obj).forEach(function(key) {
        var val = obj[key];
        var srcfile = key;
        var destfile = val;
        console.log("copying "+srcfile+" to "+destfile);
        var destdir = path.dirname(destfile);
        if (fs.existsSync(srcfile) && fs.existsSync(destdir)) {
            console.log("Do copying...");
            fs.createReadStream(srcfile).pipe(
               fs.createWriteStream(destfile));
        }
    });
});