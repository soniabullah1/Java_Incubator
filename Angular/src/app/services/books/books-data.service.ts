import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Books } from 'src/models/books';

@Injectable({
  providedIn: 'root'
})
export class BooksDataService {

  
  booksUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) { }

  getBooks(): Observable<Books[]> {
    return this.http.get<Books[]>(`${this.booksUrl}books`);
  }


  // getStockItemById(id: string): Observable<Stock> {
  //   return this.http.get<Stock>(`${this.stockUrl}Stock/${id}`);
  // }
}

