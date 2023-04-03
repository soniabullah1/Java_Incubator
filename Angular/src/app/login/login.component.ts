import { Component, OnInit } from '@angular/core';
import { User } from 'src/models/user';
import { LoginService } from '../services/login/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User[] = [];
  body: any = {}
  //dataSource: MatTableDataSource<Books>;

  constructor(private loginService: LoginService){
    //this.dataSource = new MatTableDataSource<Books>();
    this.user = [];
  }

  ngOnInit(): void {

}
  login(username: String, password: String): void {

    this.body = {
      username: username,
      password: password
    };

    this.loginService.login(this.body).subscribe((data: User[]) => {
      this.user = data;
    })
    // location.reload();
  }

}
