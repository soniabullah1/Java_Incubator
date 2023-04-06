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

  createBook(body: any): Observable<Books[]> {
    return this.http.post<Books[]>(`${this.booksUrl}books/create`, body);
  }

  updateBook(id: number, body: any): Observable<Books[]> {
    return this.http.patch<Books[]>(`${this.booksUrl}books/update/${id}`, body);
  }

  deleteBookById(id: number): Observable<Books[]> {
    return this.http.delete<Books[]>(`${this.booksUrl}books/delete/${id}`);
  }
}

