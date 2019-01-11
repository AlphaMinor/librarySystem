import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-display-items',
  templateUrl: './display-items.component.html',
  styleUrls: ['./display-items.component.css']
})
export class DisplayItemsComponent implements OnInit {

  books = [];
  dvds = [];

  displayedColumnsBooks = ['#', 'isbn', 'name', 'author', 'publisher', 'noOfPages', 'availability', 'action'];
  dataSourceBooks = new MatTableDataSource(this.books);

  displayedColumnsDVDs = ['#', 'isbn', 'name', 'language', 'producer', 'subtitle', 'actor', 'availability', 'action'];
  dataSourceDVDs = new MatTableDataSource(this.dvds);

  constructor() { }

  ngOnInit() {
    this.books = [
      {
        name: 'One',
        isbn: 1,
        author: ['Author 1'],
        publisher: 'publisher',
        availability: true,
        noOfPages: 1
      },
      {
        name: 'Two',
        isbn: 2,
        author: ['Author 1', 'Author 2', 'Author 3'],
        publisher: 'publisher',
        availability: false,
        noOfPages: 1
      },
      {
        name: 'Three',
        isbn: 3,
        author: ['Author 1'],
        publisher: 'publisher',
        availability: true,
        noOfPages: 1
      },
      {
        name: 'Four',
        isbn: 4,
        author: ['Author 1'],
        publisher: 'publisher',
        availability: true,
        noOfPages: 1
      },
      {
        name: 'Five',
        isbn: 5,
        author: ['Author 1'],
        publisher: 'publisher',
        availability: false,
        noOfPages: 1
      },
      {
        name: 'Six',
        isbn: 6,
        author: ['Author 1'],
        publisher: 'publisher',
        availability: true,
        noOfPages: 1
      },
      {
        name: 'Seven',
        isbn: 7,
        author: ['Author 1', 'Author 2', 'Author 3'],
        publisher: 'publisher',
        availability: false,
        noOfPages: 1
      },
      {
        name: 'Eight',
        isbn: 8,
        author: ['Author 1'],
        publisher: 'publisher',
        availability: true,
        noOfPages: 1
      },
      {
        name: 'Nine',
        isbn: 9,
        author: ['Author 1'],
        publisher: 'publisher',
        availability: true,
        noOfPages: 1
      },
      {
        name: 'Ten',
        isbn: 10,
        author: ['Author 1'],
        publisher: 'publisher',
        availability: false,
        noOfPages: 1
      }
    ];

    this.dvds = [
      {
        isbn: 11,
        name: 'DVD One',
        languages: ['Language 1'],
        subtitles: ['subtitle 1'],
        actors: ['actor 1'],
        availability: false,
        producer: 'producer'
      },
      {
        isbn: 12,
        name: 'DVD Two',
        languages: ['Language 1', 'Language 2', 'Language 3'],
        subtitles: ['subtitle 1', 'subtitle 2', 'subtitle 3'],
        actors: ['actor 1', 'actor 2', 'actor 3'],
        availability: false,
        producer: 'producer'
      },
      {
        isbn: 13,
        name: 'DVD Three',
        languages: ['Language 1'],
        subtitles: ['subtitle 1', 'subtitle 2', 'subtitle 3'],
        actors: ['actor 1', 'actor 2', 'actor 3'],
        availability: false,
        producer: 'producer'
      },
      {
        isbn: 14,
        name: 'DVD Four',
        languages: ['Language 1'],
        subtitles: ['subtitle 1', 'subtitle 2'],
        actors: ['actor 1', 'actor 2', 'actor 3'],
        availability: false,
        producer: 'producer'
      },
      {
        isbn: 15,
        name: 'DVD Five',
        languages: ['Language 1'],
        subtitles: ['subtitle 1', 'subtitle 2', 'subtitle 3'],
        actors: ['actor 1', 'actor 2', 'actor 3'],
        availability: true,
        producer: 'producer'
      },
      {
        isbn: 16,
        name: 'DVD Six',
        languages: ['Language 1'],
        subtitles: ['subtitle 1'],
        actors: ['actor 1'],
        availability: false,
        producer: 'producer'
      },
      {
        isbn: 17,
        name: 'DVD Seven',
        languages: ['Language 1', 'Language 2', 'Language 3'],
        subtitles: ['subtitle 1', 'subtitle 2', 'subtitle 3'],
        actors: ['actor 1', 'actor 2', 'actor 3'],
        availability: false,
        producer: 'producer'
      },
      {
        isbn: 18,
        name: 'DVD Eight',
        languages: ['Language 1'],
        subtitles: ['subtitle 1', 'subtitle 2', 'subtitle 3'],
        actors: ['actor 1', 'actor 2', 'actor 3'],
        availability: false,
        producer: 'producer'
      },
      {
        isbn: 19,
        name: 'DVD Nine',
        languages: ['Language 1'],
        subtitles: ['subtitle 1', 'subtitle 2'],
        actors: ['actor 1', 'actor 2', 'actor 3'],
        availability: false,
        producer: 'producer'
      },
      {
        isbn: 20,
        name: 'DVD Ten',
        languages: ['Language 1'],
        subtitles: ['subtitle 1', 'subtitle 2', 'subtitle 3'],
        actors: ['actor 1', 'actor 2', 'actor 3'],
        availability: true,
        producer: 'producer'
      }
    ];

    this.dataSourceBooks = new MatTableDataSource(this.books);
    this.dataSourceDVDs = new MatTableDataSource(this.dvds);
  }

  deleteBook(index) {
    this.books.splice(index, 1);
    this.dataSourceBooks = new MatTableDataSource(this.books);
  }

  deleteDVD(index) {
    this.dvds.splice(index, 1);
    this.dataSourceDVDs = new MatTableDataSource(this.dvds);
  }

  applyFilterBooks(filterValue: string) {
    this.dataSourceBooks.filterPredicate = function(data, filter: string): boolean {
      return data.name.toLowerCase().includes(filter);
    };
    this.dataSourceBooks.filter = filterValue.trim().toLowerCase();
  }

  applyFilterDVDs(filterValue: string) {
    this.dataSourceDVDs.filterPredicate = function(data, filter: string): boolean {
      return data.name.toLowerCase().includes(filter);
    };
    this.dataSourceDVDs.filter = filterValue.trim().toLowerCase();
  }

}
