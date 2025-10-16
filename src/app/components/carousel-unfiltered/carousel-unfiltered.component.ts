import { Component } from '@angular/core';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-carousel-unfiltered',
    standalone: true,
    imports: [IonicModule, CommonModule],
    templateUrl: './carousel-unfiltered.component.html',
    styleUrls: ['./carousel-unfiltered.component.scss']
})
export class CarouselUnfilteredComponent {
    slides = [
        { image: 'assets/evento1.jpg', title: 'Slide 1', description: 'Descripción del slide 1' },
        { image: 'assets/evento2.jpg', title: 'Slide 2', description: 'Descripción del slide 2' },
        { image: 'assets/evento3.jpg', title: 'Slide 3', description: 'Descripción del slide 3' }
    ]; //Filtrado de imagenes por semana

    currentIndex = 0;

    next() {
        this.currentIndex = (this.currentIndex + 1) % this.slides.length;
    }

    prev() {
        this.currentIndex = (this.currentIndex - 1 + this.slides.length) % this.slides.length;
    }

    goTo(index: number) {
        this.currentIndex = index;
    }
}
