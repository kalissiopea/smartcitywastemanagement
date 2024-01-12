import {Component, OnInit} from '@angular/core';
import {Utente} from "../../models/utente";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {postUtente} from "../../logic/reg_fs";

@Component({
  selector: 'app-cittadino',
  templateUrl: './cittadino.component.html',
  styleUrls: ['./cittadino.component.scss']
})
export class CittadinoComponent implements OnInit{
  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {

  }

  onSubmitC(){
    this.router.navigate(['/checking'])
  }

  onSubmitG(){
    this.router.navigate(['/giudizi'])
  }

  onSubmitT(){
    this.router.navigate(['/visualizzaTasse'])
  }

  onSubmitPag(){
    this.router.navigate(['/visualizzaPagamenti'])
  }

  onSubmitP(){
    this.router.navigate(['/pagare'])
  }

}
