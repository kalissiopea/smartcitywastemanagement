import { Component } from '@angular/core';
import {Observable, of} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Allarme} from "../../../models/allarme";
import {getAllarmi} from "../../../logic/allarmi_fs";

@Component({
  selector: 'app-visualizza-allarmi',
  templateUrl: './visualizza-allarmi.component.html',
  styleUrls: ['./visualizza-allarmi.component.scss']
})
export class VisualizzaAllarmiComponent {
  allarmi?: Observable<Allarme[]> = of([]);

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit(): void {
    this.allarmi = getAllarmi(this.http_c)
  }
  onSubmit0(){
    this.router.navigate(['/homeaziendarifiuti'])
  }
}
