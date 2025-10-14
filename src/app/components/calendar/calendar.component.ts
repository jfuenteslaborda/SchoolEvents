import { Component } from '@angular/core';
import { CommonModule, registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';
import { IonicModule } from '@ionic/angular';

registerLocaleData(localeEs);

@Component({
    selector: 'app-calendar-component',
    standalone: true,
    imports: [CommonModule, IonicModule],
    templateUrl: './calendar.component.html',
    styleUrls: ['./calendar.component.scss'],
})
export class CalendarComponent {
    fechaConfirmada: string = '';

    onConfirm(event: any) {
        this.fechaConfirmada = event.detail.value.split('T')[0];
        const fecha = new Date(this.fechaConfirmada);
        this.fechaConfirmada = fecha.toLocaleDateString('es-ES', {
            weekday: 'long',
            day: 'numeric',
            month: 'long',
            year: 'numeric'
        });

    }

    onCancel() {
        this.fechaConfirmada = '';
    }
}
