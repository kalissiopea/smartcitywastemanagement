import { Component } from '@angular/core';
import {Tassa} from "../../../models/tassa";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {postPagamento} from "../../../logic/pagare_fs";
import {Observable, of} from "rxjs";
import {getTasse} from "../../../logic/tasse_fs";

@Component({
  selector: 'app-visualizza-tasse',
  templateUrl: './visualizza-tasse.component.html',
  styleUrls: ['./visualizza-tasse.component.scss']
})
export class VisualizzaTasseComponent {


  tasse?: Observable<Tassa[]> = of([]);

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit(): void {
    this.tasse = getTasse(this.http_c)

  }

  onSubmit0(){
    this.router.navigate(['/citt'])
  }

  onSubmit1(){
    this.router.navigate(['/visualizzaPagamenti'])
  }

  onSubmit2(){
    this.router.navigate(['/pagare'])
  }
}
