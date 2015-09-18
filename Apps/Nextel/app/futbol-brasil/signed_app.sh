#echo "Generamos nuestro fichero de firmas (keystore)."
#keytool -genkey -v -keystore TimPalpites.keystore -alias TimPalpites -keyalg RSA -keysize 2048 -validity 10000

echo "Firmamos la aplicación con nuestra clave privada. Para ello utilizamos jarsigner."
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore keys/TimPalpites.keystore platforms/android/ant-build/CordovaApp-debug.apk TimPalpites

echo "Verificamos que la aplicación ha sido firmada."
jarsigner -verify -verbose -certs platforms/android/ant-build/TimPalpites.apk

echo "Optimizamos la aplicación con el comando zipalign."
zipalign -v 4 platforms/android/ant-build/TimPalpites.apk platforms/android/ant-build/TimPalpites.apk
