import { Component } from '@angular/core';
import {Observable, of} from "rxjs";
import {Allarme} from "../../../models/allarme";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {getAllarmi} from "../../../logic/allarmi_fs";
import {Percorso} from "../../../models/percorso";
import {getPercorso} from "../../../logic/percorso_fs";

@Component({
  selector: 'app-visualizza-percorsi',
  templateUrl: './visualizza-percorsi.component.html',
  styleUrls: ['./visualizza-percorsi.component.scss']
})
export class VisualizzaPercorsiComponent {
  percorsi?: Observable<Percorso[]> = of([]);

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit(): void {
    this.percorsi = getPercorso(this.http_c)
  }
  onSubmit0(){
    this.router.navigate(['/homeaziendarifiuti'])
  }
}
