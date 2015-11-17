#!/bin/bash
echo "###Remove Android"
cordova platform rm android
echo "###Remove iOS"
cordova platform rm ios
echo "###Remove FB plugin"
cordova plugin rm com.phonegap.plugins.facebookconnect
cordova plugin rm phonegap-facebook-plugin
echo "###Add Android"
cordova platform add android
echo "###Add iOS"
cordova platform add ios
echo "###Add FB plugin"
cordova -d plugin add https://github.com/Wizcorp/phonegap-facebook-plugin --variable APP_ID="1379325579064871" --variable APP_NAME="TIM Palpites"
#echo "###Adjust Folder Permission: "
#chown -R joe platforms/
#chown -R joe plugins/
#chown -R joe www/
#chmod -R 775 platforms/
#chmod -R 775 plugins/
#chmod -R 775 www/


