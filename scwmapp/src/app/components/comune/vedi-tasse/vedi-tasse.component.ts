import {Component, OnInit} from '@angular/core';
import {Observable, of} from "rxjs";
import {Checking} from "../../../models/checking";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {getPerformance} from "../../../logic/giudizio_fs";
import {Tassa} from "../../../models/tassa";
import {getTutte} from "../../../logic/tasse_fs";

@Component({
  selector: 'app-vedi-tasse',
  templateUrl: './vedi-tasse.component.html',
  styleUrls: ['./vedi-tasse.component.scss']
})
export class VediTasseComponent implements OnInit{

  tasse?: Observable<Tassa[]> = of([])

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {
    this.tasse = getTutte(this.http_c)
  }

  onSubmit() {
    this.router.navigate(['/comune'])
  }

}
