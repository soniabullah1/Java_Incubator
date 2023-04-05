import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { BrowseBooksComponent } from './browse-books/browse-books.component';
import { LoginComponent } from './login/login.component';
import { ManageStockComponent } from './manage-stock/manage-stock.component';
import { ManageUsersComponent } from './manage-users/manage-users.component';
import { RegistrationComponent } from './registration/registration.component';
import { ViewCartComponent } from './view-cart/view-cart.component';

const routes: Routes = [ { path: '', component: AppComponent, pathMatch: 'full' },
                         { path: 'viewCart', component: ViewCartComponent },
                         { path: 'browse', component: BrowseBooksComponent },
                         { path: 'login', component: LoginComponent },
                         { path: 'register', component: RegistrationComponent },
                         { path: 'manageStock', component: ManageStockComponent }, 
                         { path: 'manageUsers', component: ManageUsersComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
