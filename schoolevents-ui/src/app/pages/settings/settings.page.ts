import { Component, OnInit } from '@angular/core';
import {
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonAvatar,
    IonButton,
    IonIcon,
    IonList,
    IonItem,
    IonInput
} from '@ionic/angular/standalone';
import { FormsModule } from '@angular/forms';
import { addIcons } from 'ionicons';
import {
    cameraOutline,
    eyeOutline,
    eyeOffOutline,
    saveOutline,
    swapHorizontalOutline,
    logOutOutline
} from 'ionicons/icons';
import {HeaderSettingsComponent} from "../../components/header-settings/header-settings.component";
import {FooterMenuComponent} from "../../components/footer-menu/footer-menu.component";

@Component({
    selector: 'app-settings',
    templateUrl: './settings.page.html',
    styleUrls: ['./settings.page.scss'],
    standalone: true,
    imports: [
        IonContent,
        IonAvatar,
        IonButton,
        IonIcon,
        IonList,
        IonItem,
        IonInput,
        FormsModule,
        HeaderSettingsComponent,
        FooterMenuComponent
    ]
})
export class SettingsPage implements OnInit {

    username: string = 'Nombre de usuario';
    email: string = 'Example@gmail.com';
    password: string = '********';
    profileImage: string = 'assets/default-avatar.jpg';
    showPassword: boolean = false;

    constructor() {
        addIcons({
            cameraOutline,
            eyeOutline,
            eyeOffOutline,
            saveOutline,
            swapHorizontalOutline,
            logOutOutline
        });
    }

    ngOnInit() {
        // Aquí puedes cargar los datos reales del usuario
        this.loadUserData();
    }

    loadUserData() {
        // Simular carga de datos del usuario
        // En una aplicación real, esto vendría de un servicio
        this.username = 'Juan Pérez';
        this.email = 'juan.perez@example.com';
        this.password = '••••••••';
    }

    togglePasswordVisibility() {
        this.showPassword = !this.showPassword;
    }

    changeProfileImage() {
        // Lógica para cambiar la imagen de perfil
        console.log('Cambiar imagen de perfil');
        // Aquí podrías implementar la selección de imagen desde la galería o cámara
    }

    onImageError(event: any) {
        // Manejar error de carga de imagen
        event.target.src = 'assets/images/profile-placeholder.png';
    }

    saveChanges() {
        // Lógica para guardar los cambios
        console.log('Guardando cambios:', {
            username: this.username,
            email: this.email,
            password: this.password
        });

        // Aquí iría la llamada a tu servicio para actualizar los datos
        // this.userService.updateProfile({ username: this.username, email: this.email });
    }

    changeAccount() {
        // Lógica para cambiar de cuenta
        console.log('Cambiar de cuenta');
    }

    logout() {
        // Lógica para cerrar sesión
        console.log('Cerrando sesión');
    }

    isFormValid(): boolean {
        // Validación básica del formulario
        return this.username.trim().length > 0 &&
            this.email.trim().length > 0 &&
            this.validateEmail(this.email);
    }

    private validateEmail(email: string): boolean {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }
}