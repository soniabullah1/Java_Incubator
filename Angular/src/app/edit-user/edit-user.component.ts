import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { User } from 'src/models/user';
import { UserDataService } from '../services/user/user-data.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit{

  //row: any;
  isactive?: boolean;

  row: any = {}

  dataSource: MatTableDataSource<User>;

  constructor(public dialogRef: MatDialogRef<EditUserComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private userDataService: UserDataService) {
    this.row = data.row;
    this.dataSource = new MatTableDataSource<User>();
  }

  ngOnInit(): void {

  }
  onOptionChanged(event: { value: boolean | undefined; }) {
    this.isactive = event.value;
    console.log(this.isactive); // you can use console.log to see the selected value in the browser console
  }

  onUpdate() {
    this.userDataService.updateUser(this.row.bookID, this.row).subscribe((data: User[]) => {
          this.dataSource.data = data;
        })
    this.dialogRef.close();   
  }

}
