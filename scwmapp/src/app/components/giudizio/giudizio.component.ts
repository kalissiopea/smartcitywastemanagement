import {Component, OnInit} from '@angular/core';
import {Observable, of} from "rxjs";
import {Giudizio} from "../../models/giudizio";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {getGiudizio} from "../../logic/giudizio_fs";

@Component({
  selector: 'app-giudizio',
  templateUrl: './giudizio.component.html',
  styleUrls: ['./giudizio.component.scss']
})
export class GiudizioComponent implements OnInit{

  giudizi?: Observable<Giudizio[]> = of([])

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {
    this.giudizi = getGiudizio(this.http_c)
  }
  onSubmit() {
    this.router.navigate(['/comune'])
  }
}
