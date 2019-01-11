import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  displayBookForm = true;
  displayDVDForm = false;

  isbn = new FormControl('', [Validators.required]);
  name = new FormControl('', [Validators.required]);
  books: FormGroup;
  dvd = {} as any;
  book = {} as any;

  constructor(public fb: FormBuilder) {
    this.books = fb.group({
      noOfPages: [1, Validators.min(1)],
    });
  }

  ngOnInit() {
  }

  getErrorISBNMessage() {
    return this.isbn.hasError('required') ? 'You must enter a value' : '';
  }

  getErrorNameMessage() {
    return this.name.hasError('required') ? 'You must enter a value' : '';
  }

  displayBookFormMethod() {
    this.displayDVDForm = false;
    this.displayBookForm = true;
  }

  displayDVDFormMethod() {
    this.displayBookForm = false;
    this.displayDVDForm = true;
  }

  addBook(book) {
    book.noOfPages = this.books.value.noOfPages;
    console.log(book)
  }

  cancelBook() {
    this.book = {};
    this.books = this.fb.group({
      noOfPages: [1, Validators.min(1)],
    });
  }

  addDVD(dvd) {
    console.log(dvd)
  }

  cancelDVD() {
    this.dvd = {};
  }

}
