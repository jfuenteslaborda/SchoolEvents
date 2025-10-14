import { Component, OnInit } from '@angular/core';
import {FooterMenuComponent} from "../../components/footer-menu/footer-menu.component";
import {IonContent, IonHeader, IonTitle, IonToolbar} from "@ionic/angular/standalone";
import {HeaderMessagesComponent} from "../../components/header-messages/header-messages.component";

@Component({
    selector: 'app-messages',
    templateUrl: './messages.page.html',
    styleUrls: ['./messages.page.scss'],
    imports: [
        FooterMenuComponent,
        IonContent,
        IonHeader,
        IonTitle,
        IonToolbar,
        HeaderMessagesComponent,
    ]
})
export class MessagesPage implements OnInit {

  constructor() { }

  ngOnInit() {}

}
