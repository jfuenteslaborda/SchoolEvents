import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {IonAvatar, IonCard, IonItem, IonLabel, IonList, IonSpinner} from "@ionic/angular/standalone";

interface Message {
    nombre: string;
    mensaje: string;
    avatar?: string;
}

@Component({
    selector: 'app-message',
    templateUrl: './message.component.html',
    styleUrls: ['./message.component.scss'],
    imports: [
        IonSpinner,
        IonList,
        IonCard,
        IonItem,
        IonAvatar,
        IonLabel
    ]
})
export class MessageComponent implements OnInit {

    notificaciones: Message[] = [
        {
            nombre: 'Juan Pérez',
            mensaje: 'Has recibido una nueva tarea de matemáticas.',
            avatar: 'assets/juan.png'
        },
        {
            nombre: 'María López',
            mensaje: 'El evento de ciencias ha sido actualizado.',
            avatar: 'assets/maria.png'
        },
        {
            nombre: 'Carlos Sánchez',
            mensaje: 'Tu asistencia al taller de arte ha sido confirmada.',
            avatar: 'assets/carlos.png'
        },
        {
            nombre: 'Ana Gómez',
            mensaje: 'Nueva notificación de recordatorio: Reunión con padres.',
            avatar: 'assets/ana.png'
        },
        {
            nombre: 'Luis Martínez',
            mensaje: 'Tu comentario en el foro ha recibido respuesta.',
            avatar: 'assets/luis.png'
        }
    ];
    loading: boolean = true;

    constructor() {}

    ngOnInit() {

    }


}
