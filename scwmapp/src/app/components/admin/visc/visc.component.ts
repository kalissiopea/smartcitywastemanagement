import {Component, OnInit} from '@angular/core';
import {Observable, of} from "rxjs";
import {Checking} from "../../../models/checking";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {getPerformance} from "../../../logic/giudizio_fs";
import {Cassonetto} from "../../../models/cassonetto";
import {getCassonetti} from "../../../logic/admin_fs";

@Component({
  selector: 'app-visc',
  templateUrl: './visc.component.html',
  styleUrls: ['./visc.component.scss']
})
export class ViscComponent implements OnInit{

  cassonetti?: Observable<Cassonetto[]> = of([])

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {
    this.cassonetti = getCassonetti(this.http_c)
  }

  onSubmit() {
    this.router.navigate(['/admin'])
  }
}
