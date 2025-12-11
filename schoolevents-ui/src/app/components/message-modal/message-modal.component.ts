import { Component, OnInit } from '@angular/core';
import {IonicModule, ModalController} from "@ionic/angular";
import {FormsModule} from "@angular/forms";
@Component({
    selector: 'app-message-modal',
    templateUrl: './message-modal.component.html',
    styleUrls: ['./message-modal.component.scss'],
    standalone: true,
    imports: [
        IonicModule,
        FormsModule
    ]
})
export class MessageModalComponent  implements OnInit {

    nuevoMensaje: string = '';

    constructor(private modalCtrl: ModalController) {}

    cerrar() {
        this.modalCtrl.dismiss();
    }

    publicar() {
        if (this.nuevoMensaje.trim()) {
            this.modalCtrl.dismiss(this.nuevoMensaje.trim());
        }
    }



  ngOnInit() {}

}
