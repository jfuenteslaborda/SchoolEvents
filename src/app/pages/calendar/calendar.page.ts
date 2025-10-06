import { Component } from '@angular/core';
import { IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/angular/standalone';
import {HeaderCalendarComponent} from "../../components/header-calendar/header-calendar.component";
import {FooterMenuComponent} from "../../components/footer-menu/footer-menu.component";


@Component({
  selector: 'app-calendar',
  templateUrl: 'calendar.page.html',
  styleUrls: ['calendar.page.scss'],
    imports: [
        HeaderCalendarComponent,
        IonToolbar,
        IonTitle,
        IonContent,
        IonHeader,
        FooterMenuComponent


    ],
})
export class CalendarPage {
  constructor() {}
}
