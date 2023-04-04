import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Cart } from 'src/models/cart';
import { CartItems } from 'src/models/cartItems';
import { EditCartItem } from 'src/models/editCartItem';

@Injectable({
  providedIn: 'root'
})
export class CartDataService {

  cartUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) { }

  getCart(): Observable<Cart[]> {
    return this.http.get<Cart[]>(`${this.cartUrl}carts`);
  }

  getCartById(id: number): Observable<Cart[]> {
    return this.http.get<Cart[]>(`${this.cartUrl}carts/${id}`);
  }

  getCartItemById(id: number): Observable<CartItems[]> {
    return this.http.get<CartItems[]>(`${this.cartUrl}carts/item/${id}`);
  }

  deleteItem(cartItemID: number): Observable<CartItems[]> {
    return this.http.patch<CartItems[]>(`${this.cartUrl}carts/deleteItem/${cartItemID}`, cartItemID);
  }

  editItem(body: any): Observable<EditCartItem[]> {
    console.log("body: ", body);
    return this.http.patch<EditCartItem[]>(`${this.cartUrl}carts/editItem`, body);
  }

}
