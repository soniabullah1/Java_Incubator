import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { User } from 'src/models/user';
import { Cart } from 'src/models/cart';

@Injectable({
  providedIn: 'root'
})
export class RegistrationDataService {

  registrationUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) { }

  register(body: any): Observable<User[]> {
    return this.http.post<User[]>(`${this.registrationUrl}users/register`, body);
  }

  createCartForUser(body: any): Observable<Cart[]> {
    return this.http.post<Cart[]>(`${this.registrationUrl}carts/create`, body);
  }
}
