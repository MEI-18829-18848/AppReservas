import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  	providedIn: 'root'
})
export class ImageService {

	public image: string;

	constructor(private http: HttpClient) { 
		this.loadImage();
	} 

	loadImage(){
        localStorage.setItem('user_image', 'https://randomuser.me/api/portraits/women/46.jpg');
		//this.http.get<any>('https://randomuser.me/api/?inc=picture').subscribe(x=> localStorage.setItem('user_image', x.results[0].picture.large));
	}

	getImage(): string{
		return localStorage.getItem("user_image")
	}

}
