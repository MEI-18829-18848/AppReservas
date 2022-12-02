import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  public get backend() : string {
    return 'https://quiet-crag-88107.herokuapp.com/';
    // return 'https://localhost:7064/';
  }

  public get frontend() : string {
    return 'http://localhost:4200/';
  }
  
  constructor() { }
}
