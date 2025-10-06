import { Component, OnInit } from '@angular/core';
import {FooterMenuComponent} from "../../components/footer-menu/footer-menu.component";
import {HeaderCalendarComponent} from "../../components/header-calendar/header-calendar.component";
import {IonContent, IonHeader, IonTitle, IonToolbar} from "@ionic/angular/standalone";
import {HeaderSettingsComponent} from "../../components/header-settings/header-settings.component";

@Component({
    selector: 'app-settings',
    templateUrl: './settings.page.html',
    styleUrls: ['./settings.page.scss'],
    imports: [
        FooterMenuComponent,
        HeaderCalendarComponent,
        IonContent,
        IonHeader,
        IonTitle,
        IonToolbar,
        HeaderSettingsComponent
    ]
})
export class SettingsPage implements OnInit {

  constructor() { }

  ngOnInit() {}

}
