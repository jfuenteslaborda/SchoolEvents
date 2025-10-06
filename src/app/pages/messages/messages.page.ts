import { Component, OnInit } from '@angular/core';
import {FooterMenuComponent} from "../../components/footer-menu/footer-menu.component";
import {HeaderCalendarComponent} from "../../components/header-calendar/header-calendar.component";
import {IonContent, IonHeader, IonTitle, IonToolbar} from "@ionic/angular/standalone";
import {HeaderMessagesComponent} from "../../components/header-messages/header-messages.component";
import {HeaderSettingsComponent} from "../../components/header-settings/header-settings.component";

@Component({
    selector: 'app-messages',
    templateUrl: './messages.page.html',
    styleUrls: ['./messages.page.scss'],
    imports: [
        FooterMenuComponent,
        HeaderCalendarComponent,
        IonContent,
        IonHeader,
        IonTitle,
        IonToolbar,
        HeaderMessagesComponent,
        HeaderSettingsComponent
    ]
})
export class MessagesPage implements OnInit {

  constructor() { }

  ngOnInit() {}

}
