import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { User } from 'src/models/user';
import { LoginService } from '../services/login/login.service';
import { Router } from '@angular/router';
import { BooksDataService } from '../services/books/books-data.service';
import { CartItems } from 'src/models/cartItems';
import { Books } from 'src/models/books';
import { CartDataService } from '../services/cart/cart-data.service';

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
  role?: string;
  message : string = ''
  status: Boolean = false;
  userRole?: string;

  //dataSource: MatTableDataSource<Books>;

  @ViewChild('errorMessage') errorMessage: ElementRef;

  showError() {
    this.errorMessage.nativeElement.textContent = 'The username or password provided is incorrect. Please try again.';
  }

  constructor(private loginService: LoginService, private router: Router){
    //this.dataSource = new MatTableDataSource<Books>();
    this.user = [];
    this.errorMessage = new ElementRef(document.getElementById('errorMessage'));
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
      if (this.user) {
        console.log("testing:", this.user);
        this.status = true;
        localStorage.setItem('isLoggedIn', 'true');

        this.loginService.getRoleByUsername(this.body.username).subscribe((currentRole: User) => {
          if (currentRole && currentRole.role) {
            console.log("The current role is: ", currentRole.role);
            this.userRole = currentRole.role;
          } else {
            console.log("No role found for username:", this.body.username);
          }
        });
        
        

        // this.role = this.getRoleByUsername(this.body.username);

        console.log("current role is: ", this.userRole);

        this.router.navigate(['/browse']);
      }

      else {

        this.showError();
        
      }
      
    })
    // location.reload();
    console.log("status: " + this.userRole);
  }


  logout() {
    localStorage.removeItem('isLoggedIn');
    this.status = false;
    this.router.navigate(['/login']);
  }

  manageStock() {
    this.router.navigate(['/manageStock']);
  }

  manageUsers() {
    this.router.navigate(['/manageUsers']);
  }

}
