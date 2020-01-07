import { Injectable } from '@angular/core';
import * as moment from 'moment';
import { Platform } from '@ionic/angular';

var companyName = '1234';
var buildVersion = '9';
var serverVersion = '1';
var bundleVersion = '0.0.2';
var bundleId = '';
var updateInfo = {};
var realWidth = 0;
var realHeight = 0;
var touchType = 'click';
// var device = {
//   platform:''
// };

declare var navigator; 
declare var Connection;
declare var device;

@Injectable({
  providedIn: 'root'
})
export class ConfigurationsService {

  backButtonSubscription: any;


  constructor( public platform: Platform) { }

  getCompanyName() {
    return companyName;
  }

  setCompanyName(name) {
    companyName = name;
  }

  getBuildVersion() {
    return buildVersion;
  }

  setBuildVersion(version) {
    buildVersion = version;
  }

  getServerVersion() {
    return serverVersion;
  }

  setServerVersion(version) {
    serverVersion = version;
  }

  getBundleVersion() {
    return bundleVersion;
  }


  setBundleVersion(version) {
    bundleVersion = version;
  }

  getBundleId() {
    return bundleId;
  }

  setBundleId(id) {
    bundleId = id;
  }

  getUpdateInfo() {
    return updateInfo;
  }

  setUpdateInfo(info) {
    updateInfo = info;
  }

  setRealWidth(val){
    try{
        if(val != null && val != "" && !isNaN(val)){
            realWidth = parseInt(val);
        }
    }catch(e){
        console.log("Bad width: " + e);
    }
  }

 getRealWidth(){
    if(realWidth != 0){
        return realWidth;
    }else{
        return window.innerWidth;
    }
 }

setRealHeight(val){
    try{
        if(val != null && val != "" && !isNaN(val)){
            realHeight = parseInt(val);
        }
    }catch(e){
        console.log("Bad height: " + e);
    }
}

getRealHeight(){
    if(realHeight != 0){
        return realHeight;
    }else{
        return window.innerHeight;
    }
}


getPlatform(){
 if (this.platform.is('ios')) {
     return 'iOS';
  }else if(this.platform.is('android')){
    return 'Android';
  }else {
    return 'Web';
  }
}


getUpstreamChannel(){
    if(this.platform.is('android')){
        return 'Android';
    } else if(this.platform.is('ios')){
        return 'iOS';
    } else {
        return 'Web';
    }
}

getDeviceId(){
    if(this.platform.is('android')){
        return 1;
    } else if(this.platform.is('ios')){
        return 2;
    } else  {
        return 3;
    }
}

setTouchType(){
    if(this.platform.is('ios')){
        touchType = "touchend";
    }else if(this.platform.is('android')){
        touchType = "click";
    }else{
        touchType = "click";
    }
}


getGMT(prefix){
    var tz =  moment().format('[GMT]ZZ').replace(/\s/g, '');
    tz = encodeURIComponent(tz);
    //return prefix + 'timezoneName=' + tz;
    return prefix + 'timezoneName=GMT-0500'; //Cable!
}

}
