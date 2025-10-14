import { Component } from '@angular/core';
import { IonContent } from '@ionic/angular/standalone';
import {HeaderCalendarComponent} from "../../components/header-calendar/header-calendar.component";
import {FooterMenuComponent} from "../../components/footer-menu/footer-menu.component";
import {CalendarComponent} from "../../components/calendar/calendar.component";


@Component({
  selector: 'app-calendar',
  templateUrl: 'calendar.page.html',
  styleUrls: ['calendar.page.scss'],
    imports: [
        HeaderCalendarComponent,
        IonContent,
        FooterMenuComponent,
        CalendarComponent
    ],
})
export class CalendarPage {
  constructor() {}
}
