import { Component, OnInit } from '@angular/core';
import { IonAvatar, IonCard, IonItem, IonLabel, IonList, IonSpinner } from "@ionic/angular/standalone";
import { CommonModule } from "@angular/common";

interface Message {
    nombre: string;
    mensaje: string;
    avatar?: string;
}

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
        IonSpinner
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

    loading = true;

    ngOnInit() {
        setTimeout(() => {
            this.loading = false;
        }, 1000);
    }
}
