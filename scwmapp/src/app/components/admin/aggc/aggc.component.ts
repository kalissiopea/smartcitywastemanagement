import {Component, OnInit} from '@angular/core';
import {Utente} from "../../../models/utente";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {postCassonetto, postUser} from "../../../logic/admin_fs";
import {Cassonetto} from "../../../models/cassonetto";

@Component({
  selector: 'app-aggc',
  templateUrl: './aggc.component.html',
  styleUrls: ['./aggc.component.scss']
})
export class AggcComponent implements OnInit{

  cassonetto: Cassonetto = {
    id:'',
    luogo:'',
    tipo:'',
    stato:0,
  }

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {

  }

  onSubmit() {
    postCassonetto(this.http_c, this.cassonetto);
    this.router.navigate(['/admin'])
  }

  onSubmit1() {
    this.router.navigate(['/admin'])
  }

}
