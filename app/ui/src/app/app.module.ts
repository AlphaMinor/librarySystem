import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatFormFieldModule, MatInputModule, MatTableModule, MatButtonModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { DisplayItemsComponent } from './display-items/display-items.component';
import { AddItemComponent } from './add-item/add-item.component';
import { BorrowItemComponent } from './borrow-item/borrow-item.component';
import { ReturnItemComponent } from './return-item/return-item.component';
import { AddReaderComponent } from './add-reader/add-reader.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    DisplayItemsComponent,
    AddItemComponent,
    BorrowItemComponent,
    ReturnItemComponent,
    AddReaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatTableModule,
    MatButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
