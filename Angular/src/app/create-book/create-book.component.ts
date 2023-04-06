import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Books } from 'src/models/books';
import { BooksDataService } from '../services/books/books-data.service';

@Component({
  selector: 'app-create-book',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent implements OnInit{

  isactive?: boolean;

  title?: string;
  author?: string;
  version?: number | null;
  dateOfPublication?: Date| null;
  numberOfBooksInStock?: number | null;
  price?: number| null;

  body: any = {}

  dataSource: MatTableDataSource<Books>;

  constructor(public dialogRef: MatDialogRef<CreateBookComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private booksDataService: BooksDataService) {
    this.body = data.row;
    this.dataSource = new MatTableDataSource<Books>();
  }

  ngOnInit(): void {
  }
  
  onCreate() {

    this.body = {
      title: this.title,
      author: this.author,
      version: this.version,
      dateOfPublication: this.dateOfPublication,
      numberOfBooksInStock: this.numberOfBooksInStock,
      price: this.price
    };

    console.log("Creating... ", this.body)
    this.booksDataService.createBook(this.body).subscribe((data: Books[]) => {
          this.dataSource.data = data;
          location.reload();
        })
    this.dialogRef.close();   
  }

}
