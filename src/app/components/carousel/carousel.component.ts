import { Component } from '@angular/core';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-carousel',
    standalone: true,
    imports: [IonicModule, CommonModule],
    templateUrl: './carousel.component.html',
    styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent {
    slides = [
        { image: 'assets/slide1.jpg', title: 'Slide 1', description: 'Descripción del slide 1' },
        { image: 'assets/slide2.jpg', title: 'Slide 2', description: 'Descripción del slide 2' },
        { image: 'assets/slide3.jpg', title: 'Slide 3', description: 'Descripción del slide 3' }
    ];

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
