import {Component, OnInit} from '@angular/core';
import {Observable, of} from "rxjs";
import {Checking} from "../../../models/checking";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {getChecking, getGiudizi} from "../../../logic/checking_fs";
import {Giudizio} from "../../../models/giudizio";

@Component({
  selector: 'app-giudizi',
  templateUrl: './giudizi.component.html',
  styleUrls: ['./giudizi.component.scss']
})
export class GiudiziComponent  implements OnInit{

  giudizi?: Observable<Giudizio[]> = of([])

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {
    this.giudizi = getGiudizi(this.http_c)
  }

  onSubmit() {
    this.router.navigate(['/citt'])
  }

}
