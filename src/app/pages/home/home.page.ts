import { Component } from '@angular/core';
import { IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/angular/standalone';
import {HeaderCalendarComponent} from "../../components/header-calendar/header-calendar.component";


@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
    imports: [
        HeaderCalendarComponent,
        IonToolbar,
        IonTitle,
        IonContent,
        IonHeader


    ],
})
export class HomePage {
  constructor() {}
}
