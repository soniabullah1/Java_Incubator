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
  status: Boolean = false;
  //dataSource: MatTableDataSource<Books>;

  constructor(private loginService: LoginService, private router: Router){
    //this.dataSource = new MatTableDataSource<Books>();
    this.user = [];
  }

  ngOnInit(): void {
    const isLoggedIn = localStorage.getItem('isLoggedIn');
  if (isLoggedIn === 'true') {
    this.status = true;
  }

}
  login(username?: String, password?: String): void {

    this.body = {
      username: this.username,
      password: this.password
    };

    this.loginService.login(this.body).subscribe((data: User[]) => {
      this.user = data;
      this.status = true;
      if (this.user) {
        this.status = true;
        localStorage.setItem('isLoggedIn', 'true');

        this.router.navigate(['/browse']);
      }

      else {
        this.message = 'The username or password provided is incorrect. Please try again.';
        // document.getElementById('error-message').textContent = this.message;

        document.addEventListener('DOMContentLoaded', () => {
          const errorMessage = document.getElementById('error-message');
          if (errorMessage) {
            errorMessage.textContent = 'The username or password provided is incorrect. Please try again.';
          }
        });
        
      }
      
    })
    // location.reload();
    console.log("status: " + this.status);
  }


  logout() {
    localStorage.removeItem('isLoggedIn');
    this.status = false;
    this.router.navigate(['/login']);
  }

}
