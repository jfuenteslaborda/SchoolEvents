import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'calendar',
        loadComponent: () => import('./pages/calendar/calendar.page').then((m) => m.CalendarPage),
    },
    {
        path: 'messages',
        loadComponent: () =>
            import('./pages/messages/messages.page').then((m) => m.MessagesPage),
    },
    {
        path: 'settings',
        loadComponent: () =>
            import('./pages/settings/settings.page').then((m) => m.SettingsPage),
    },
    {
        path: '',
        redirectTo: 'calendar',
        pathMatch: 'full',
    },
];
