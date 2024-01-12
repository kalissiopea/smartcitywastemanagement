import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Percorso} from "../../../models/percorso";
import {postPercorso} from "../../../logic/percorso_fs";

@Component({
  selector: 'app-istanzia-percorso-pulizia',
  templateUrl: './istanzia-percorso-pulizia.component.html',
  styleUrls: ['./istanzia-percorso-pulizia.component.scss']
})
export class IstanziaPercorsoPuliziaComponent {


  percorso: Percorso={
    percorso: "",
    date: ""
  }

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  onSubmit(){
    postPercorso(this.http_c, this.percorso)
    this.router.navigate(['/homeaziendarifiuti'])
  }
  onSubmit1(){
    this.router.navigate(['/homeaziendarifiuti'])
  }
}
