import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { User } from 'src/models/user';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  loginUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) { }

  // login(body: any): Observable<User[]> {
  //   console.log("logged in!", this.http.post<User[]>(`${this.loginUrl}users/login`, body));
  //   return this.http.post<User[]>(`${this.loginUrl}users/login`, body);
  // }

  login(body: any): Observable<User[]> {
    return this.http.post<User[]>(`${this.loginUrl}users/login`, body).pipe(
      map(users => {
        if (users.length === 0) {
          throw new Error("No user found.");
        }
        console.log("users: ", users)
        return users;
      })
    );
  }
  

}
