import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../../interfaces/User';
import { UserStadistics } from '../../interfaces/UserStadistics';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    private http = inject(HttpClient);

    private currentUserSubject = new BehaviorSubject<User | null>(null);
    public currentUser$ = this.currentUserSubject.asObservable();

    constructor() {
        const storedUser = localStorage.getItem('usuarioLogueado');
        if (storedUser) {
            this.currentUserSubject.next(JSON.parse(storedUser));
        }
    }

    obtenerUsuarios(): Observable<User[]> {
        return this.http.get<User[]>(`/api/users/all`);
    }

    obtenerUsuarioPorId(id: number): Observable<User> {
        return this.http.get<User>(`/api/users/by_id/${id}`);
    }

    obtenerUsuarioPorEmail(email: string): Observable<User> {
        return this.http.get<User>(`/api/users/by_email/${email}`);
    }

    obtenerEstadisticasUsuarios(): Observable<UserStadistics> {
        return this.http.get<UserStadistics>(`/api/users/stadistic`);
    }

    crearUsuario(user: User): Observable<User> {
        return this.http.post<User>(`/api/users/create`, user);
    }

    actualizarUsuario(id: number, user: User): Observable<User> {
        return this.http.put<User>(`/api/users/update/${id}`, user);
    }

    eliminarUsuario(id: number): Observable<void> {
        return this.http.delete<void>(`/api/users/delete/${id}`);
    }

    setUsuarioLogueado(user: User) {
        localStorage.setItem('usuarioLogueado', JSON.stringify(user));
        this.currentUserSubject.next(user);
    }

    logout() {
        localStorage.removeItem('usuarioLogueado');
        this.currentUserSubject.next(null);
    }

    getUsuarioActual(): User | null {
        return this.currentUserSubject.value;
    }

    private userKey = 'usuarioLogueado';

    setUser(user: any): void {
        const usuario: User = {
            id: user.id,
            full_name: user.full_name,
            email: user.email,
            password: user.password,
            photo: user.photo,
            date: user.date,
            is_admin: user.is_admin
        };

        localStorage.setItem('usuarioLogueado', JSON.stringify(usuario));
        this.currentUserSubject.next(usuario);
    }


    getUser() {
        const user = localStorage.getItem(this.userKey);
        return user ? JSON.parse(user) : null;
    }

    private getUsuarioDesdeStorage(): User | null {
        const stored = localStorage.getItem('usuarioLogueado');
        if (stored) {
            try {
                const user = JSON.parse(stored) as User;
                user.is_admin = !!(user as any).is_admin;
                return user;
            } catch (err) {
                console.warn('Error parseando usuario en localStorage', err);
                localStorage.removeItem('usuarioLogueado');
                return null;
            }
        }
        return null;
    }

    clearUser() {
        localStorage.removeItem(this.userKey);
    }
}
