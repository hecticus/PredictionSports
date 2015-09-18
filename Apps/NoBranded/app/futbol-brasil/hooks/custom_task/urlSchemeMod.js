#!/usr/bin/env node
'use strict';

var plist = require('plist');
var fs = require('fs');
var path = require('path');

console.log("#####HOOK to fix URL SCHEME!");
var filePath = 'platforms/ios/TIM Palpites/TIM Palpites-Info.plist';

if(fs.existsSync(filePath)){
	console.log(filePath + ' exists!!!');	
	var obj = plist.parse(fs.readFileSync(filePath, 'utf8'));
	if((typeof obj.CFBundleURLTypes !== 'undefined') && 
		(typeof obj.CFBundleURLTypes[0].CFBundleURLSchemes !== 'undefined')){
		obj.CFBundleURLTypes[0].CFBundleURLSchemes.push('timfutebol');
		//console.log(obj.CFBundleURLTypes[0].CFBundleURLSchemes);
		//console.log(plist.build(obj));
		fs.writeFileSync(filePath, plist.build(obj));	
	}	
}