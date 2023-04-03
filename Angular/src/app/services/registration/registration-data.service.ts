import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { User } from 'src/models/user';

@Injectable({
  providedIn: 'root'
})
export class RegistrationDataService {

  registrationUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) { }

    register(body: any): Observable<User[]> {
    return this.http.post<User[]>(`${this.registrationUrl}users/register`, body);
  }
}
