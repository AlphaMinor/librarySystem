import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-return-item',
  templateUrl: './return-item.component.html',
  styleUrls: ['./return-item.component.css']
})
export class ReturnItemComponent implements OnInit {

  isbn = new FormControl('', [Validators.required]);
  book = {} as any;

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

  returnItem(reader, book) {
    console.log(reader);
    console.log(book);
  }

}

