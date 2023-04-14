import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/models/cart';
import { CartItems } from 'src/models/cartItems';
import { EditCartItem } from 'src/models/editCartItem';
import { CartDataService } from '../services/cart/cart-data.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-view-cart',
  templateUrl: './view-cart.component.html',
  styleUrls: ['./view-cart.component.css']
})
export class ViewCartComponent implements OnInit {

  carts: Cart;
  cartItems: CartItems[] = [];
  cartItemsTest: CartItems[] = [];
  editItems: EditCartItem[] = [];
  body: any = {}

  quantity?: any;
  price?: any;
  total = 0;
  totalPrice = 0;
  //dataSource: MatTableDataSource<Books>;

  constructor(private cartDataService: CartDataService, private location: Location){
    //this.dataSource = new MatTableDataSource<Books>();
    this.carts = {
      cartID: 0,
      cartItems: []
    };
    this.cartItems = [];
    this.editItems = [];
  }

  ngOnInit(): void {

    const userIDFromStorage = localStorage.getItem('userID');
    const usersID = parseInt(userIDFromStorage ?? '');

    this.cartDataService.getCartById(usersID).subscribe((data: Cart) => {
      this.carts = data;
      console.log(this.carts);
    });
}


calculateTotalPrice(price: any, quantity: any): number {
  this.total = quantity * price;
  console.log("hiii")
  this.totalPrice += this.total
  return this.total;
}

updateTotalPrice(quantity: any, price: any): number {
  const totalPriceForItem = quantity * price;
  this.totalPrice += totalPriceForItem;
  return this.totalPrice;
}

removeItem(id: any): void {
  this.cartDataService.deleteItem(id).subscribe((data: CartItems[]) => {
    this.cartItems = data;
    console.log("item deleted: ", this.cartItems)
  })
  location.reload();
}

increaseQuantity(id: any, quantity: any, availableBooks: any): void {

  const userIDFromStorage = localStorage.getItem('userID');
  const usersID = parseInt(userIDFromStorage ?? '');

  console.log("available: ", availableBooks)
  if(quantity + 1 > availableBooks){
  this.body = {
    cartItemID: id,
    userID: usersID,
    quantity: quantity
  };
}

else{
  this.body = {
    cartItemID: id,
    userID: usersID,
    quantity: quantity + 1
  };
}

  this.cartDataService.editItem(this.body).subscribe((data: EditCartItem[]) => {
    this.editItems = data;
    console.log("item edited: ", this.editItems)
  })
  location.reload();
}

decreaseQuantity(id: any, quantity: any): void {

  const userIDFromStorage = localStorage.getItem('userID');
  const usersID = parseInt(userIDFromStorage ?? '');

  if (quantity - 1 <= 0){
  this.body = {
    cartItemID: id,
    userID: usersID,
    quantity: 1
  };
}

  else{
    this.body = {
      cartItemID: id,
      userID: usersID,
      quantity: quantity - 1
    };
  }

  this.cartDataService.editItem(this.body).subscribe((data: EditCartItem[]) => {
    this.editItems = data;
    console.log("item edited: ", this.editItems)
  })

  location.reload();

}

}
