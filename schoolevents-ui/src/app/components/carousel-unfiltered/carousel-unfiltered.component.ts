import { Component, OnInit, OnDestroy } from '@angular/core';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { Router } from "@angular/router";
import { Event } from "../../interfaces/Event";
import { EventService } from "../../services/eventService/event-service";
import { UserService } from '../../services/userService/user-service';
import { Subscription } from 'rxjs';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
    selector: 'app-carousel-unfiltered',
    standalone: true,
    imports: [IonicModule, CommonModule, ReactiveFormsModule],
    templateUrl: './carousel-unfiltered.component.html',
    styleUrls: ['./carousel-unfiltered.component.scss']
})
export class CarouselUnfilteredComponent implements OnInit, OnDestroy {

    eventos: Event[] = []; // Almacenar los eventos completos
    slides: { image: string; title: string; description: string; }[] = [];

    currentIndex = 0;
    loading = false;
    error = '';

    // --- Lógica de Admin/Edición ---
    isAdmin: boolean = false;
    private userSubscription: Subscription | null = null;
    showEditModal: boolean = false;
    editEventForm: FormGroup;
    imagePreviewSrc: string | ArrayBuffer | null = null;
    editingEventId: number | null = null;

    constructor(
        private router: Router,
        private eventService: EventService,
        private userService: UserService,
        private fb: FormBuilder
    ) {
        this.editEventForm = this.fb.group({
            id: [null],
            title: ['', [Validators.required, Validators.minLength(5)]],
            date: ['', Validators.required],
            description: ['', [Validators.required, Validators.maxLength(250)]],
            capacity: [1, [Validators.required, Validators.min(1)]],
            price: [0, [Validators.required, Validators.min(0)]],
            need_payment: [false, Validators.required],
            src: ['', Validators.required]
        });
    }

    ngOnInit() {
        this.userSubscription = this.userService.currentUser$.subscribe(user => {
            this.isAdmin = !!user?.is_Admin;
        });
        this.cargarEventosProximasDosSemanas();
    }

    ngOnDestroy() {
        this.userSubscription?.unsubscribe();
    }

    cargarEventosProximasDosSemanas() {
        this.loading = true;
        this.eventos = [];
        this.slides = [];

        this.eventService.obtenerEventosProximasDosSemanas().subscribe({
            next: (events: Event[]) => {
                this.eventos = events; // Guardar eventos completos
                this.slides = events.map(event => ({
                    image: event.src || 'assets/default-event.jpg',
                    title: event.title,
                    description: event.description || ''
                }));
                this.loading = false;
                this.currentIndex = 0;
            },
            error: (err) => {
                this.error = 'No se pudieron cargar los eventos';
                this.loading = false;
            }
        });
    }

    // UTILS DEL CARRUSEL
    get eventoActual(): Event | null {
        return this.eventos.length > 0 ? this.eventos[this.currentIndex] : null;
    }

    next() {
        if (!this.slides.length) return;
        this.currentIndex = (this.currentIndex + 1) % this.slides.length;
    }

    prev() {
        if (!this.slides.length) return;
        this.currentIndex = (this.currentIndex - 1 + this.slides.length) % this.slides.length;
    }

    goTo(index: number) {
        if (!this.slides.length) return;
        this.currentIndex = index;
    }

    goToPage() {
        if (!this.eventoActual) return;
        this.eventService.setEventoActivo(this.eventoActual);
        this.router.navigate(['/event']);
    }

    // --- LÓGICA DE EDICIÓN ---

    editarEvento() {
        const evento = this.eventoActual;
        if (!evento) return;

        this.editingEventId = evento.id || null;
        this.imagePreviewSrc = evento.src;

        const [day, month, year] = evento.date.split('-');
        const formattedDate = `${year}-${month}-${day}`;

        this.editEventForm.setValue({
            id: evento.id || null,
            title: evento.title,
            date: formattedDate,
            description: evento.description,
            capacity: evento.capacity,
            price: evento.price,
            need_payment: evento.need_payment,
            src: evento.src
        });

        this.showEditModal = true;
    }

    closeEditModal() {
        this.showEditModal = false;
        this.editEventForm.reset();
        this.imagePreviewSrc = null;
        this.editingEventId = null;
        this.cargarEventosProximasDosSemanas(); // Refrescar
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
                    this.editEventForm.get('src')?.setValue(base64String);
                };
                reader.readAsDataURL(file);
            }
        };
        input.click();
    }

    saveEditEvent() {
        if (this.editEventForm.invalid || !this.editingEventId) {
            this.editEventForm.markAllAsTouched();
            return;
        }

        const formData = this.editEventForm.value;

        const [year, month, day] = formData.date.split('-');
        const formattedDate = `${day}-${month}-${year}`;

        const updatedEvent: Event = {
            id: this.editingEventId,
            title: formData.title,
            date: formattedDate,
            description: formData.description,
            capacity: +formData.capacity,
            price: +formData.price,
            need_payment: formData.need_payment === true || formData.need_payment === 'true',
            src: formData.src
        } as Event;

        this.eventService.actualizarEvento(this.editingEventId, updatedEvent).subscribe({
            next: (response) => {
                this.closeEditModal();
            },
            error: (err) => {
                alert('Error al actualizar el evento.');
            }
        });
    }

    // --- LÓGICA DE ELIMINACIÓN ---

    eliminarEvento() {
        const evento = this.eventoActual;
        if (!evento || !evento.id) return;

        if (confirm('¿Estás seguro de que quieres eliminar el evento: ' + evento.title + '?')) {
            const eventId = evento.id;

            this.eventService.eliminarEvento(eventId).subscribe({
                next: () => {
                    this.cargarEventosProximasDosSemanas();
                },
                error: (err) => {
                    alert('Hubo un error al intentar eliminar el evento.');
                }
            });
        }
    }
}