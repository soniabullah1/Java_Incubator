import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { User } from 'src/models/user';
import { UserDataService } from '../services/user/user-data.service';
import { EditUserComponent } from '../edit-user/edit-user.component';
import { RegistrationComponent } from '../registration/registration.component';
import { CreateUserComponent } from '../create-user/create-user.component';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {
  isLoading = true;

  displayedColumns: string[] = ['userID', 'username', 'email', 'dateOfBirth', 'role', 'edit', 'delete'];
  public users?: User;

  username: string = '';
  email: string = '';
  dateOfBirth: Date | null = null;
  role: string = '';

  startingSearchSeries: number | null = null;
  startingSearchCondition: string = "None";
  startingSearchQuantity: number | null = null;

  row: any;
  user: User[] = [];

  dataSource: MatTableDataSource<User>;

  constructor(private userDataService: UserDataService, public dialog: MatDialog){
    this.dataSource = new MatTableDataSource<User>(this.user);
    this.user = [];

  }
  ngOnInit(): void {
    this.userDataService.getUsers().subscribe((data: User[]) => {
      this.user = data;
      this.dataSource = new MatTableDataSource<User>(this.user); // Create MatTableDataSource instance
    });
  }

  openDialog(row: any): void {
    const dialogRef = this.dialog.open(EditUserComponent, {
      data: { row }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  deleteUser(id: any): void {
    this.userDataService.deleteUserById(id).subscribe((data: User[]) => {
      this.user = data;
    })
    location.reload();
  }

  openDialogCreate(row: any): void {
    const dialogRef = this.dialog.open(CreateUserComponent, {
      data: { row }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}