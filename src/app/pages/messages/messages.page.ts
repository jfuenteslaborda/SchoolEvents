import { Component, OnInit } from '@angular/core';
import {FooterMenuComponent} from "../../components/footer-menu/footer-menu.component";
import { IonContent } from '@ionic/angular/standalone';
import {HeaderMessagesComponent} from "../../components/header-messages/header-messages.component";
import {MessageComponent} from "../../components/message/message.component";
import {MessageModalComponent} from "../../components/message-modal/message-modal.component";

@Component({
    selector: 'app-messages',
    standalone: true,
    templateUrl: './messages.page.html',
    styleUrls: ['./messages.page.scss'],
    imports: [
        FooterMenuComponent,
        IonContent,
        HeaderMessagesComponent,
        MessageComponent,
        MessageModalComponent,
    ]
})
export class MessagesPage implements OnInit {

  constructor() { }

  ngOnInit() {}

}
