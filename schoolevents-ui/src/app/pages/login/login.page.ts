import { Component} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import {IonContent, IonFooter, IonRouterLink} from "@ionic/angular/standalone";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
  standalone: true,
    imports: [CommonModule, FormsModule, ReactiveFormsModule, IonContent, IonFooter]
})
export class LoginPage {

    loginForm: FormGroup;

    constructor(private fb: FormBuilder, private router:Router) {
        this.loginForm = this.fb.group({
            correo: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.minLength(6)]],
        });
    }

    get correo() {
        return this.loginForm.get('correo');
    }

    get password() {
        return this.loginForm.get('password');
    }

    onSubmit() {
        if (this.loginForm.invalid) {
            this.loginForm.markAllAsTouched();
            return;
        }

        const { correo, password } = this.loginForm.value;
        console.log('Datos enviados:', correo, password);
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
