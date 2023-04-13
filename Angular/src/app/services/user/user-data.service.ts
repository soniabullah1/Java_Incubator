import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { User } from 'src/models/user';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  userUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.userUrl}users`);
  }

  getIDByUsername(username: string): Observable<User> {
    return this.http.get<User>(`${this.userUrl}users/findID/${username}`);
  }

  createUser(body: any): Observable<User[]> {
    return this.http.post<User[]>(`${this.userUrl}users/create`, body);
  }

  updateUser(id: number, body: any): Observable<User[]> {
    return this.http.patch<User[]>(`${this.userUrl}users/update/${id}`, body);
  }

  deleteUserById(id: number): Observable<User[]> {
    return this.http.delete<User[]>(`${this.userUrl}users/delete/${id}`);
  }
}
