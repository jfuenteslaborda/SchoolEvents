import {Component, Injectable} from '@angular/core';
import { IonicModule, ModalController, IonButton } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { CommentModalComponent } from 'src/app/components/comment-modal/comment-modal.component'

interface Comentario {
    username: string;
    profilePic: string;
    comment: string;
}

@Component({
    selector: 'app-comment',
    standalone: true,
    imports: [IonicModule, CommonModule],
    templateUrl: './comment.component.html',
    styleUrls: ['./comment.component.scss'],
})

export class CommentComponent {
    comentarios: Comentario[] = [
        {
            username: 'Juan Pérez',
            profilePic: 'assets/juan.jpg',
            comment: '¡Este es un comentario de prueba!'
        },
        {
            username: 'Ana López',
            profilePic: 'assets/ana.jpg',
            comment: 'Me encanta esta publicación 😊'
        },
        {
            username: 'Carlos García',
            profilePic: 'assets/carlos.jpg',
            comment: 'Muy interesante, gracias por compartir.'
        },
        {
            username: 'Lucía Fernández',
            profilePic: 'assets/lucia.jpg',
            comment: '¡Totalmente de acuerdo con esto!'
        }
    ];

    constructor(private modalCtrl: ModalController) {}

    async abrirModal() {
        const modal = await this.modalCtrl.create({
            component: CommentModalComponent,
        });

        modal.onDidDismiss().then((result) => {
            if (result.data) {
                this.comentarios.push({
                    username: 'Tú',
                    profilePic: 'assets/profile-placeholder.png',
                    comment: result.data,
                });
            }
        });

        await modal.present();
    }
}
