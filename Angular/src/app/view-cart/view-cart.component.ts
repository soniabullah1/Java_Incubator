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

  carts: Cart[] = [];
  cartItems: CartItems[] = [];
  editItems: EditCartItem[] = [];
  body: any = {}
  //dataSource: MatTableDataSource<Books>;

  constructor(private cartDataService: CartDataService, private location: Location){
    //this.dataSource = new MatTableDataSource<Books>();
    this.carts = [];
    this.cartItems = [];
    this.editItems = [];
  }

  ngOnInit(): void {

    this.cartDataService.getCart().subscribe((data: Cart[]) => {
      this.carts = data;
      console.log(this.carts);
    });
}

removeItem(id: any): void {
  this.cartDataService.deleteItem(id).subscribe((data: CartItems[]) => {
    this.cartItems = data;
    console.log("item deleted: ", this.cartItems)
  })
  location.reload();
}

increaseQuantity(id: any, quantity: any, availableBooks: any): void {

  console.log("available: ", availableBooks)
  if(quantity + 1 > availableBooks){
  this.body = {
    cartItemID: id,
    customerID: 1,
    quantity: quantity
  };
}

else{
  this.body = {
    cartItemID: id,
    customerID: 1,
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

  if (quantity - 1 <= 0){
  this.body = {
    cartItemID: id,
    customerID: 1,
    quantity: 1
  };
}

  else{
    this.body = {
      cartItemID: id,
      customerID: 1,
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
