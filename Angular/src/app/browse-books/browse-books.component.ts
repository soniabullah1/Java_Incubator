import { Component, OnInit } from '@angular/core';
import { AddToCart } from 'src/models/addToCart';
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
  carts: AddToCart[] = [];
  body: any = {}
  //dataSource: MatTableDataSource<Books>;

  constructor(private booksDataService: BooksDataService){
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
  this.body = {
    bookID: id,
    customerID: 1,
    quantity: 1
  };
  this.booksDataService.addItemToCart(this.body).subscribe((data: AddToCart[]) => {
    this.carts = data;
    console.log("item added: ", this.carts)
  })
}

// getBooks(): void {
//   this.booksDataService.getBooks().subscribe((data: BooksData) => {
//     this.dataSource.data = data.data;
//   });
// }
}

