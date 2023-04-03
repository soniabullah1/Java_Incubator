import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/models/user';
import { RegistrationDataService } from '../services/registration/registration-data.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user: User[] = [];
  body: any = {}
  username?: string;
  password?: string;
  email?: string;
  dateOfBirth?: Date;
  role?: string;
  message : string = ''
  //dataSource: MatTableDataSource<Books>;

  constructor(private registrationDataService: RegistrationDataService, private router: Router){
    //this.dataSource = new MatTableDataSource<Books>();
    this.user = [];
  }

  ngOnInit(): void {

}
  register(email?: String, username?: String, password?: String, dateOfBirth?: Date, role?: String): void {

    this.body = {
      email: this.email,
      username: this.username,
      password: this.password,
      dateOfBirth: this.dateOfBirth,
      role: this.role
    };

    this.registrationDataService.register(this.body).subscribe((data: User[]) => {
      this.user = data;
      if (this.user) {
        console.log("test: ", (this.user).length);
        this.router.navigate(['/login']);
      }

      else{
        this.message = 'The username or password provided is incorrect. Please try again.'
      }
    })
    // location.reload();
  }

}

