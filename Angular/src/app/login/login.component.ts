import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { User } from 'src/models/user';
import { LoginService } from '../services/login/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User[] | undefined= [];
  body: any = {}
  username?: string;
  password?: string;
  role?: string;
  message : string = ''
  status: Boolean = false;
  userRole?: any;

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
    const currentUsersRole = localStorage.getItem('currentUsersRole');
  
  if (isLoggedIn === 'true') {
    this.status = true;
  }

  if (currentUsersRole === 'true') {
    this.userRole = "Admin";
  }

}
async login(username?: String, password?: String): Promise<void> {

  this.body = {
    username: this.username,
    password: this.password
  };

  try {
    const data: User[] | undefined = await this.loginService.login(this.body).toPromise();
    this.user = data;
    if (this.user) {
      console.log("testing:", this.user);
      this.status = true;
      localStorage.setItem('isLoggedIn', 'true');

      const currentRole: User | undefined = await this.loginService.getRoleByUsername(this.body.username).toPromise();
      if (currentRole && currentRole.role) {
        this.userRole = currentRole.role;

        if (this.userRole === 'Admin'){
          localStorage.setItem('currentUsersRole', 'true');
        }

       else {
          localStorage.setItem('currentUsersRole', 'false');
        }

      } else {
        console.log("No role found for username:", this.body.username);
      }

      this.router.navigate(['/browse']);
    } else {
      this.showError();
    }

  } catch (error) {
    console.log("Error: ", error);
    this.showError();
  }

  console.log("status: " + this.userRole);
}



  logout() {
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('userRole');
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
