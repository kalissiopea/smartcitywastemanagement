import {Component, OnInit} from '@angular/core';
import {Utente} from "../../models/utente";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {postUtente} from "../../logic/reg_fs";

@Component({
  selector: 'app-reg',
  templateUrl: './reg.component.html',
  styleUrls: ['./reg.component.scss']
})
export class RegComponent implements OnInit{

  public showPassword = false;

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
    console.log(this.utente)
    postUtente(this.http_c, this.utente);
    this.router.navigate(['/login'])
  }

}
