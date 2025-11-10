import { Component } from '@angular/core';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import {NavigationEnd, Router} from "@angular/router";
import {filter} from "rxjs/operators";

@Component({
    selector: 'app-carousel-images',
    standalone: true,
    imports: [IonicModule, CommonModule],
    templateUrl: './carousel-images.component.html',
    styleUrls: ['./carousel-images.component.scss']
})
export class CarouselImagesComponent {
    slides = [
        { image: 'assets/evento1.jpg', title: 'Slide 1', description: 'Descripción del slide 1' },
        { image: 'assets/evento2.jpg', title: 'Slide 2', description: 'Descripción del slide 2' },
        { image: 'assets/evento3.jpg', title: 'Slide 3', description: 'Descripción del slide 3' }
    ];

    currentIndex = 0;

    constructor(private router: Router) {}


    next() {
        this.currentIndex = (this.currentIndex + 1) % this.slides.length;
    }

    prev() {
        this.currentIndex = (this.currentIndex - 1 + this.slides.length) % this.slides.length;
    }

}
