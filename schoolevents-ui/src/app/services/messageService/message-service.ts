import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Message } from 'src/app/interfaces/Message'


@Injectable({
    providedIn: 'root'
})
export class MessageService {
    private apiUrl = 'http://localhost:8080/messages'; // URL base del backend

    constructor(private http: HttpClient) {}

    getAllMessages(): Observable<Message[]> {
        return this.http.get<Message[]>(`${this.apiUrl}/all`);
    }

    getMessageById(id: number): Observable<Message> {
        return this.http.get<Message>(`${this.apiUrl}/by_id/${id}`);
    }

    getMessagesByUser(id: number): Observable<Message[]> {
        return this.http.get<Message[]>(`${this.apiUrl}/by_user/${id}`);
    }

    createMessage(message: Message): Observable<Message> {
        return this.http.post<Message>(`${this.apiUrl}/create`, message);
    }

    updateMessage(id: number, message: Message): Observable<Message> {
        return this.http.put<Message>(`${this.apiUrl}/update/${id}`, message);
    }

    deleteMessage(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
    }
}
