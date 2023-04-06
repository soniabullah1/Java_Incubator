import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Books } from 'src/models/books';
import { BooksDataService } from '../services/books/books-data.service';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit{

  //row: any;
  isactive?: boolean;

  row: any = {}

  dataSource: MatTableDataSource<Books>;

  constructor(public dialogRef: MatDialogRef<EditBookComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private bookDataService: BooksDataService) {
    this.row = data.row;
    this.dataSource = new MatTableDataSource<Books>();
  }

  ngOnInit(): void {

  }
  onOptionChanged(event: { value: boolean | undefined; }) {
    this.isactive = event.value;
    console.log(this.isactive); // you can use console.log to see the selected value in the browser console
  }

  onUpdate() {
    this.bookDataService.updateBook(this.row.bookID, this.row).subscribe((data: Books[]) => {
          this.dataSource.data = data;
        })
    this.dialogRef.close();   
  }

}
