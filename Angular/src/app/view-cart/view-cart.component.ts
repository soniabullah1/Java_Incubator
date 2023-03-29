import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/models/cart';
import { CartItems } from 'src/models/cartItems';
import { CartDataService } from '../services/cart/cart-data.service';

@Component({
  selector: 'app-view-cart',
  templateUrl: './view-cart.component.html',
  styleUrls: ['./view-cart.component.css']
})
export class ViewCartComponent implements OnInit {

  carts: Cart[] = [];
  cartItems: CartItems[] = [];
  //dataSource: MatTableDataSource<Books>;

  constructor(private cartDataService: CartDataService){
    //this.dataSource = new MatTableDataSource<Books>();
    this.carts = [];
    this.cartItems = [];
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
}
}
