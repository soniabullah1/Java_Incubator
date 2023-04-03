import { Component, OnInit } from '@angular/core';
import { User } from 'src/models/user';
import { LoginService } from '../services/login/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User[] = [];
  body: any = {}
  username?: string;
  password?: string;
  message : string = ''
  //dataSource: MatTableDataSource<Books>;

  constructor(private loginService: LoginService, private router: Router){
    //this.dataSource = new MatTableDataSource<Books>();
    this.user = [];
  }

  ngOnInit(): void {

}
  login(username?: String, password?: String): void {

    this.body = {
      username: this.username,
      password: this.password
    };

    this.loginService.login(this.body).subscribe((data: User[]) => {
      this.user = data;
      if (this.user) {
        console.log("test: ", (this.user).length);
        this.router.navigate(['/browse']);
      }

      else{
        this.message = 'The username or password provided is incorrect. Please try again.'
      }
    })
    // location.reload();
  }

}
