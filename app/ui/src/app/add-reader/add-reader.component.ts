import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-add-reader',
  templateUrl: './add-reader.component.html',
  styleUrls: ['./add-reader.component.css']
})
export class AddReaderComponent implements OnInit {

  id = new FormControl('', [Validators.required]);
  firstName = new FormControl('', [Validators.required]);
  email = new FormControl('', [Validators.required, Validators.email]);
  phoneNumber = new FormControl('', [Validators.minLength(10), Validators.maxLength(10)]);
  reader = {} as any;

  constructor() {
  }

  ngOnInit() {
  }

  getErrorIdMessage() {
    return this.id.hasError('required') ? 'You must enter a value' : '';
  }

  getErrorReaderFirstNameMessage() {
    return this.firstName.hasError('required') ? 'You must enter a value' : '';
  }

  getErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
      this.email.hasError('email') ? 'Not a valid email' :
        '';
  }

  getErrorPhoneNumberMessage() {
    return this.phoneNumber.hasError('minLength') ? 'Invalid Phone Number' :
      this.phoneNumber.hasError('maxLength') ? 'Invalid Phone Number' :
        '';
  }

  addReader(reader) {
    console.log(reader);
  }

}
