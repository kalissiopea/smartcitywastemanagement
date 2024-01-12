import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Giudizio} from "../../../models/giudizio";
import {postGiudizio} from "../../../logic/giudizio_fs";

@Component({
  selector: 'app-aggiungi',
  templateUrl: './aggiungi.component.html',
  styleUrls: ['./aggiungi.component.scss']
})
export class AggiungiComponent implements OnInit{

  giudizio: Giudizio = {
    id:'',
    username:'',
    giudizio:'',
    punteggio:0,
    date:''
  }

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {

  }

  onSubmit() {
    postGiudizio(this.http_c, this.giudizio)
    this.router.navigate(['/comune'])
  }
}
