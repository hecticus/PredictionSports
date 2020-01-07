import { Injectable } from '@angular/core';
import { LoadingController } from '@ionic/angular';
import { AlertController } from '@ionic/angular';
import { ToastController } from '@ionic/angular';

@Injectable({
	providedIn: 'root'
})
export class LoadingService {

	constructor(public toastController : ToastController,public loadingController: LoadingController, public alertController: AlertController) { }

	 async presentLoading() {
		const loading = await this.loadingController.create({
			spinner: "crescent",
			message: 'Espera por favor...',
			duration: 900,
			animated: true
		});
		await loading.present();

		const { role, data } = await loading.onDidDismiss();

		console.log('Loading dismissed!');
	}

	async presentLoadingWithOptions() {
		const loading = await this.loadingController.create({
			spinner: null,
			duration: 5000,
			message: 'Please wait...',
			translucent: true,
			cssClass: 'custom-class custom-loading'
		});
		return await loading.present();
	}

	async presentAlert(sms) {
		const alert = await this.alertController.create({
			header: 'Alert',
			message:sms,
			backdropDismiss: false,
			buttons: ['OK']
		});

		await alert.present();
	}

	async presentAlertConfirm(tit, sub, sms, opt) {
		const alert = await this.alertController.create({
			header: tit,
			subHeader: sub,
			message: sms,
			buttons: opt,
			backdropDismiss : false,
		});

		await alert.present();
	}

	 async presentToast(sms,color) {
    const toast = await this.toastController.create({
      message: sms,
      color: color,
      duration: 2000,
      position: "top"
    });
    toast.present();
  }
}
