import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { BrowseBooksComponent } from './browse-books/browse-books.component';
import { ViewCartComponent } from './view-cart/view-cart.component';

const routes: Routes = [ { path: '', component: AppComponent, pathMatch: 'full' },
                         { path: 'viewCart', component: ViewCartComponent },
                         { path: 'browse', component: BrowseBooksComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
