import { Component, OnInit } from '@angular/core';
import { Books } from 'src/models/books';
import { BooksDataService } from '../services/books/books-data.service';

@Component({
  selector: 'app-browse-books',
  templateUrl: './browse-books.component.html',
  styleUrls: ['./browse-books.component.css']
})
export class BrowseBooksComponent implements OnInit {

  titles: Books[] = [];
  //dataSource: MatTableDataSource<Books>;

  constructor(private booksDataService: BooksDataService){
    //this.dataSource = new MatTableDataSource<Books>();
    this.titles = [];
  }

  ngOnInit(): void {

    this.booksDataService.getBooks().subscribe((data: Books[]) => {
      this.titles = data;
    });
}

// getBooks(): void {
//   this.booksDataService.getBooks().subscribe((data: BooksData) => {
//     this.dataSource.data = data.data;
//   });
// }
}

