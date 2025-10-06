import { Component, OnInit } from '@angular/core';
import {IonHeader, IonIcon, IonToolbar} from "@ionic/angular/standalone";


@Component({
    selector: 'app-header-calendar',
    templateUrl: './header-calendar.component.html',
    styleUrls: ['./header-calendar.component.scss'],
    imports: [
        IonHeader,
        IonToolbar,
        IonIcon
    ],
    standalone: true
})
export class HeaderCalendarComponent  implements OnInit {

  constructor() {

  }

  ngOnInit() {}

}
