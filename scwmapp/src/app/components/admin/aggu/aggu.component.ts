import {Component, OnInit} from '@angular/core';
import {Utente} from "../../../models/utente";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {postUtente} from "../../../logic/reg_fs";
import {postUser} from "../../../logic/admin_fs";

@Component({
  selector: 'app-aggu',
  templateUrl: './aggu.component.html',
  styleUrls: ['./aggu.component.scss']
})
export class AgguComponent implements OnInit{

  utente: Utente = {
    id:'',
    nome:'',
    cognome:'',
    email:'',
    eta:0,
    username:'',
    password:'',
    ruolo:'',
    indirizzo:'',
    card:''
  }

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {

  }

  onSubmit() {
    postUser(this.http_c, this.utente);
    this.router.navigate(['/admin'])
  }

  onSubmit1() {
    this.router.navigate(['/admin'])
  }
}
