import { Component, OnInit  } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Books } from 'src/models/books';
import { BooksData } from 'src/models/booksData';
import { BooksDataService } from './services/books/books-data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Angular';

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
