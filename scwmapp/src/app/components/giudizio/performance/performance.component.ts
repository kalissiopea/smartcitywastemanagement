import {Component, OnInit} from '@angular/core';
import {Observable, of} from "rxjs";
import {Giudizio} from "../../../models/giudizio";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {getGiudizio, getPerformance, postGiudizio} from "../../../logic/giudizio_fs";
import {Checking} from "../../../models/checking";

@Component({
  selector: 'app-performance',
  templateUrl: './performance.component.html',
  styleUrls: ['./performance.component.scss']
})
export class PerformanceComponent implements OnInit{

  checking?: Observable<Checking[]> = of([])

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {
    this.checking = getPerformance(this.http_c)
  }

  onSubmit() {
    this.router.navigate(['/comune'])
  }
}
