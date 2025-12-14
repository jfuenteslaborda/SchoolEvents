import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {
    IonContent,
    IonButton,
    IonModal,
    IonHeader,
    IonToolbar,
    IonTitle,
    IonButtons,
    IonItem,
    IonLabel,
    IonInput,
    IonTextarea,
    IonToggle,
    IonIcon
} from '@ionic/angular/standalone';
import { HeaderCalendarComponent } from "../../components/header-calendar/header-calendar.component";
import { FooterMenuComponent } from "../../components/footer-menu/footer-menu.component";
import { CalendarComponent } from "../../components/calendar/calendar.component";
import { CarouselComponent } from "../../components/carousel/carousel.component";
import { CarouselUnfilteredComponent } from "../../components/carousel-unfiltered/carousel-unfiltered.component";
import { UserService } from '../../services/userService/user-service';
import { EventService } from '../../services/eventService/event-service';
import { User } from '../../interfaces/User';
import { Event } from '../../interfaces/Event';
import { Subscription } from 'rxjs';

@Component({
    selector: 'app-calendar',
    templateUrl: './calendar.page.html',
    styleUrls: ['./calendar.page.scss'],
    standalone: true,
    imports: [
        CommonModule,
        ReactiveFormsModule,
        IonButton,
        IonContent,
        IonModal, IonHeader, IonToolbar, IonTitle, IonButtons, IonItem, IonLabel, IonInput, IonTextarea, IonToggle,
        HeaderCalendarComponent,
        FooterMenuComponent,
        CalendarComponent,
        CarouselComponent,
        CarouselUnfilteredComponent, IonIcon
    ]
})
export class CalendarPage implements OnInit, OnDestroy {

    fechaSeleccionada: string = '';
    isAdmin: boolean = false;
    showEventModal: boolean = false;
    private userSub: Subscription | null = null;

    eventForm: FormGroup;
    imagePreviewSrc: string | ArrayBuffer | null = null;

    constructor(
        private userService: UserService,
        private eventService: EventService,
        private fb: FormBuilder
    ) {
        this.eventForm = this.fb.group({
            title: ['', [Validators.required, Validators.minLength(5)]],
            date: ['', Validators.required],
            description: ['', [Validators.required, Validators.maxLength(250)]],
            capacity: [1, [Validators.required, Validators.min(1)]],
            price: [0, [Validators.required, Validators.min(0)]],
            need_payment: [false, Validators.required],
            src: ['', Validators.required] // <-- Requiere el Base64
        });
    }

    ngOnInit() {
        this.userSub = this.userService.currentUser$.subscribe((user: User | null) => {
            this.isAdmin = !!user?.is_Admin;
        });

        const currentUser = this.userService.getUsuarioActual();
        if (currentUser) {
            this.isAdmin = !!currentUser.is_Admin;
        }
    }

    ngOnDestroy() {
        this.userSub?.unsubscribe();
    }

    onFechaSeleccionada(fecha: string) {
        this.fechaSeleccionada = fecha;
    }

    openEventModal() {
        this.showEventModal = true;
        if (this.fechaSeleccionada) {
            this.eventForm.patchValue({ date: this.fechaSeleccionada });
        }
    }

    closeEventModal() {
        this.showEventModal = false;
        this.eventForm.reset();
        this.imagePreviewSrc = null;
    }

    handleImageUpload() {
        const input = document.createElement('input');
        input.type = 'file';
        input.accept = 'image/*';

        input.onchange = (event: any) => {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = (e: any) => {
                    const base64String = e.target.result;
                    this.imagePreviewSrc = base64String;

                    this.eventForm.get('src')?.setValue(base64String);
                    console.log('Imagen seleccionada. SRC del formulario actualizada con Base64.');
                };
                reader.readAsDataURL(file);
            }
        };
        input.click();
    }

    saveEvent() {
        if (this.eventForm.invalid) {
            this.eventForm.markAllAsTouched();
            return;
        }

        const formData = this.eventForm.value;

        const [year, month, day] = formData.date.split('-');
        const formattedDate = `${day}-${month}-${year}`;

        const newEvent: Event = {
            title: formData.title,
            date: formattedDate,
            description: formData.description,
            capacity: +formData.capacity,
            price: +formData.price,
            need_payment: formData.need_payment === true || formData.need_payment === 'true',
            src: formData.src
        } as Event;

        this.eventService.crearEvento(newEvent).subscribe({
            next: (response) => {
                console.log('Evento creado con éxito:', response);
                this.closeEventModal();
            },
            error: (err) => {
                console.error('Error al crear evento:', err);
            }
        });
    }
}