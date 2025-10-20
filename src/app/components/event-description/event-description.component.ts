import { Component, Input } from '@angular/core';
import { AlertController } from '@ionic/angular';
import {
    IonButton,
    IonCard,
    IonCardContent,
    IonCardHeader,
    IonCardSubtitle,
    IonCardTitle
} from "@ionic/angular/standalone";

@Component({
    selector: 'app-event-description',
    templateUrl: './event-description.component.html',
    styleUrls: ['./event-description.component.scss'],
    imports: [
        IonCard,
        IonCardHeader,
        IonCardTitle,
        IonCardSubtitle,
        IonCardContent,
        IonButton
    ]
})
export class EventDescriptionComponent {

     event = {
        tittle: 'Example',
        date: '12-12-12',
        description: 'Esto es un ejemplo aaaaaaaaaaaaaaaaaaaaaa dios mio aaaaaaaaaaaaaaaa',
        isFree: false
    };



    constructor(private alertCtrl: AlertController) {}

    async suscribe() {
        if (this.event?.isFree) {
            const alert = await this.alertCtrl.create({
                header: 'Inscripción completa',
                message: 'Te has inscrito gratis al evento.',
                buttons: ['OK'],
            });
            await alert.present();
        } else {
            const alert = await this.alertCtrl.create({
                header: 'Pago requerido',
                message: 'Este evento requiere pago. Procede con el pago para inscribirte.',
                buttons: [
                    { text: 'Cancelar', role: 'cancel'},
                    { text: 'Pagar', handler: () => this.paymentProcedure() }
                ],
            });
            await alert.present();
        }
    }

    paymentProcedure() {
        console.log('Redirigiendo al pago...');
    }

    back() {
        console.log('Volver a la pantalla anterior');
    }
}
