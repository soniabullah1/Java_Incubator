import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { User } from 'src/models/user';
import { RegistrationDataService } from '../services/registration/registration-data.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user: User[] = [];
  body: any = {}
  
  username?: string;
  password?: string;
  email?: string;
  dateOfBirth?: Date| null;
  role?: string;
  message : string = ''
  //dataSource: MatTableDataSource<Books>;

  dataSource: MatTableDataSource<User>;

  constructor(public dialogRef: MatDialogRef<CreateUserComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private registrationDataService: RegistrationDataService) {
    this.body = data.row;
    this.dataSource = new MatTableDataSource<User>();
  }

  ngOnInit(): void {
  }
  
  onCreate() {

    this.body = {
      username: this.username,
      password: this.password,
      email: this.email,
      dateOfBirth: this.dateOfBirth,
      role: this.role
    };

    console.log("Creating... ", this.body)
    this.registrationDataService.register(this.body).subscribe((data: User[]) => {
          this.dataSource.data = data;
          location.reload();
        })
    this.dialogRef.close();   
  }

}

