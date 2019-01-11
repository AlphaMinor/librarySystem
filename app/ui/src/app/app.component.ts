import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  link = 'home';

  // Method that is called when the user clicks on a nav menu
  private navigate(route) {
    this.link = route;
  }
}
