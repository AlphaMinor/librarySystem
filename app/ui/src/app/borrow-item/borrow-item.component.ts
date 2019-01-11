import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-borrow-item',
  templateUrl: './borrow-item.component.html',
  styleUrls: ['./borrow-item.component.css']
})
export class BorrowItemComponent implements OnInit {

  isbn = new FormControl('', [Validators.required]);
  book = {} as any;

  available = false;
  display = false;

  readerId = new FormControl('', [Validators.required]);
  reader = {} as any;

  constructor() {
  }

  ngOnInit() {
  }

  getErrorISBNMessage() {
    return this.isbn.hasError('required') ? 'You must enter a value' : '';
  }

  getErrorReaderIdMessage() {
    return this.readerId.hasError('required') ? 'You must enter a value' : '';
  }

  checkAvailability(book) {
    console.log(book);
    this.display = true;
    this.available = true;
  }

  borrowItem(reader) {
    console.log(reader);
  }

}
