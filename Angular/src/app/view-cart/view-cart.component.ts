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
  //dataSource: MatTableDataSource<Books>;

  constructor(private cartDataService: CartDataService){
    //this.dataSource = new MatTableDataSource<Books>();
    this.carts = [];
  }

  ngOnInit(): void {

    this.cartDataService.getCart().subscribe((data: Cart[]) => {
      this.carts = data;
      console.log(this.carts);
    });
}
}
