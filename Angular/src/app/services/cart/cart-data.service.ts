import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Cart } from 'src/models/cart';
import { CartItems } from 'src/models/cartItems';

@Injectable({
  providedIn: 'root'
})
export class CartDataService {

  cartUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) { }

  getCart(): Observable<Cart[]> {
    return this.http.get<Cart[]>(`${this.cartUrl}carts`);
  }

  addItemToCart(body: any): Observable<CartItems[]> {
    return this.http.patch<CartItems[]>(`${this.cartUrl}carts/addItem`, body);
  }

}
