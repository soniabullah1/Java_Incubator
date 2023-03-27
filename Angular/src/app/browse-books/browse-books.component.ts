import { Component, OnInit } from '@angular/core';
import { Books } from 'src/models/books';
import { Cart } from 'src/models/cart';
import { CartItems } from 'src/models/cartItems';
import { BooksDataService } from '../services/books/books-data.service';
import { CartDataService } from '../services/cart/cart-data.service';

@Component({
  selector: 'app-browse-books',
  templateUrl: './browse-books.component.html',
  styleUrls: ['./browse-books.component.css']
})
export class BrowseBooksComponent implements OnInit {

  titles: Books[] = [];
  carts: CartItems[] = [];
  //dataSource: MatTableDataSource<Books>;

  constructor(private booksDataService: BooksDataService, private cartDataService: CartDataService){
    //this.dataSource = new MatTableDataSource<Books>();
    this.titles = [];
    this.carts = [];
  }

  ngOnInit(): void {

    this.booksDataService.getBooks().subscribe((data: Books[]) => {
      this.titles = data;
    });
}

addItemToCart(id: any): void {
  this.cartDataService.addItemToCart(id).subscribe((data: CartItems[]) => {
    this.carts = data;
    console.log("item added: ", id)
  })
}

// getBooks(): void {
//   this.booksDataService.getBooks().subscribe((data: BooksData) => {
//     this.dataSource.data = data.data;
//   });
// }
}

