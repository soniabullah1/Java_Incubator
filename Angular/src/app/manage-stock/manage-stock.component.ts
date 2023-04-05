import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Books } from 'src/models/books';
import { BooksDataService } from '../services/books/books-data.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-manage-stock',
  templateUrl: './manage-stock.component.html',
  styleUrls: ['./manage-stock.component.css']
})
export class ManageStockComponent implements OnInit {
  isLoading = true;

  displayedColumns: string[] = ['bookID', 'title', 'author', 'version', 'dateOfPublication', 'numberOfBooksInStock', 'price', 'edit', 'delete'];
  public books?: Books;

  title: string = '';
  author: string = '';
  version: number | null = null;
  dateOfPublication: Date | null = null;
  numberOfBooksInStock: number | null = null;
  price: number | null = null;

  startingSearchSeries: number | null = null;
  startingSearchCondition: string = "None";
  startingSearchQuantity: number | null = null;

  row: any;
  titles: Books[] = [];

  dataSource: MatTableDataSource<Books>;

  constructor(private booksDataService: BooksDataService, public dialog: MatDialog){
    this.dataSource = new MatTableDataSource<Books>(this.titles);
    this.titles = [];

  }
  ngOnInit(): void {
    this.booksDataService.getBooks().subscribe((data: Books[]) => {
      this.titles = data;
      this.dataSource = new MatTableDataSource<Books>(this.titles); // Create MatTableDataSource instance
    });
  }
  

}
