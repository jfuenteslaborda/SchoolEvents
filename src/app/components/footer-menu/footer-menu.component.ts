import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {IonIcon} from "@ionic/angular/standalone";

@Component({
    selector: 'app-footer-menu',
    templateUrl: './footer-menu.component.html',
    styleUrls: ['./footer-menu.component.scss'],
    imports: [
        IonIcon
    ]
})
export class FooterMenuComponent {
    expanded = false;

    constructor(private router: Router) {}

    toggleMenu() {
        this.expanded = !this.expanded;
    }

    navigateWithAnimation(route: string, event: any) {
        const icon = event.target;
        icon.classList.add('clicked');

        // Espera a que termine la animación antes de navegar
        setTimeout(() => {
            icon.classList.remove('clicked');
            this.router.navigate([route]);
        }, 300); // coincide con la duración de @keyframes click-pop
    }
}
