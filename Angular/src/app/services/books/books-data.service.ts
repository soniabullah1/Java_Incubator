import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Books } from 'src/models/books';
import { CartItems } from 'src/models/cartItems';
import { AddToCart } from 'src/models/addToCart';

@Injectable({
  providedIn: 'root'
})
export class BooksDataService {

  
  booksUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) { }

  getBooks(): Observable<Books[]> {
    return this.http.get<Books[]>(`${this.booksUrl}books`);
  }

  addItemToCart(body: any): Observable<AddToCart[]> {
    return this.http.patch<AddToCart[]>(`${this.booksUrl}carts/addItem`, body);
  }

  getBookById(id: number): Observable<Books[]> {
    return this.http.get<Books[]>(`${this.booksUrl}books/${id}`);
  }
}

