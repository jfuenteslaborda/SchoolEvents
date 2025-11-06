import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ReactiveFormsModule, FormBuilder, FormGroup, Validators, FormsModule} from '@angular/forms';
import {IonContent, IonFooter} from "@ionic/angular/standalone";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
  standalone: true,
    imports: [CommonModule, FormsModule, ReactiveFormsModule, IonFooter, IonContent]
})
export class RegisterPage  {

    registerForm: FormGroup;

    constructor(private fb: FormBuilder, private router: Router) {
        this.registerForm = this.fb.group({
            nombre: ['', [Validators.required, Validators.minLength(3)]],
            fechaNacimiento: ['', Validators.required],
            correo: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.minLength(6)]],
        });
    }

    get nombre() {
        return this.registerForm.get('nombre');
    }

    get fechaNacimiento() {
        return this.registerForm.get('fechaNacimiento');
    }

    get correo() {
        return this.registerForm.get('correo');
    }

    get password() {
        return this.registerForm.get('password');
    }

    onSubmit() {
        if (this.registerForm.invalid) {
            this.registerForm.markAllAsTouched();
            return;
        }

        const { nombre, fechaNacimiento, correo, password } = this.registerForm.value;
        console.log('Datos registrados:', nombre, fechaNacimiento, correo, password);

    }

    navigateWithAnimation(route: string, event: any) {
        const icon = event.target;
        icon.classList.add('clicked');

        setTimeout(() => {
            icon.classList.remove('clicked');
            this.router.navigate([route]);
        }, 300);
    }
}
