import { Component, OnInit } from '@angular/core';
import {IonAvatar, IonButton, IonCard, IonItem, IonLabel, IonList, IonSpinner} from "@ionic/angular/standalone";
import { CommonModule } from "@angular/common";
import {ModalController} from "@ionic/angular";
import {MessageModalComponent} from "../message-modal/message-modal.component";
import {MessageService} from "../../services/MessageService";

@Component({
    selector: 'app-message',
    standalone: true,
    templateUrl: './message.component.html',
    styleUrls: ['./message.component.scss'],
    imports: [
        CommonModule,
        IonList,
        IonCard,
        IonItem,
        IonAvatar,
        IonLabel,
        IonSpinner,
        IonButton,
    ]
})
export class MessageComponent implements OnInit {

    constructor(private modalCtrl: ModalController, private messageService: MessageService) { }

    messages: Message[] = [];

    //newMessage: Message;
    /*
    notificaciones: Message[] = [
        {
            nombre: 'Juan Pérez',
            mensaje: 'Has recibido una nueva tarea de matemáticas.'
        },
        {
            nombre: 'María López',
            mensaje: 'El evento de ciencias ha sido actualizado.'
        },
        {
            nombre: 'Carlos Sánchez',
            mensaje: 'Tu asistencia al taller de arte ha sido confirmada.'
        },
        {
            nombre: 'Ana Gómez',
            mensaje: 'Nueva notificación de recordatorio: Reunión con padres.'
        },
        {
            nombre: 'Luis Martínez',
            mensaje: 'Tu comentario en el foro ha recibido respuesta.'
        }
    ];*/

    loading = true;

    ngOnInit() {
        setTimeout(() => {
            this.loading = false;
        }, 1000);

        this.messageService.getAllMessages().subscribe(
            data => this.messages = data
        )
    }
/*
    async abrirModal() {
        const modal = await this.modalCtrl.create({
            component: MessageModalComponent,
        });


        modal.onDidDismiss().then((result) => {
            if (result.data) {

                this.messages.push({
                    nombre: 'Tú',
                    mensaje: result.data,
                });


                this.newMessage.content = result.data
                this.newMessage.user
                this.messageService.createMessage(
                )
            }
        });

        await modal.present();
    }*/
}
