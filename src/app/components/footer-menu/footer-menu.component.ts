import { Component } from '@angular/core';
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

    toggleMenu() {
        this.expanded = !this.expanded;
    }
}
