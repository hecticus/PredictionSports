cordova platform rm android
cordova plugin rm com.phonegap.plugins.facebookconnect
cordova platform add android
cordova -d plugin add https://github.com/Wizcorp/phonegap-facebook-plugin --variable APP_ID="1379325579064871" --variable APP_NAME="TIM Palpites"
cordova -d plugin add https://github.com/EddyVerbruggen/LaunchMyApp-PhoneGap-Plugin.git --variable URL_SCHEME="timfutebol"
