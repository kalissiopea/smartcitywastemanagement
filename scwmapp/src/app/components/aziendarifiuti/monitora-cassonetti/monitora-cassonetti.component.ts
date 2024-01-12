import { Component } from '@angular/core';
import {Observable, of} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Cassonetto} from "../../../models/cassonetto";
import {getCassonetti} from "../../../logic/cassonetti_fs";

@Component({
  selector: 'app-monitora-cassonetti',
  templateUrl: './monitora-cassonetti.component.html',
  styleUrls: ['./monitora-cassonetti.component.scss']
})
export class MonitoraCassonettiComponent {
  cassonetti?: Observable<Cassonetto[]> = of([]);


  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit(): void {
    this.cassonetti = getCassonetti(this.http_c);

  }

  onSubmit0(){
    this.router.navigate(['/homeaziendarifiuti'])
  }
}
