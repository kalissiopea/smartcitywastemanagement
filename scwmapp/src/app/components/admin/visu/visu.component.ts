import {Component, OnInit} from '@angular/core';
import {Observable, of} from "rxjs";
import {Cassonetto} from "../../../models/cassonetto";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {getCassonetti, getUtenti} from "../../../logic/admin_fs";
import {Utente} from "../../../models/utente";

@Component({
  selector: 'app-visu',
  templateUrl: './visu.component.html',
  styleUrls: ['./visu.component.scss']
})
export class VisuComponent implements OnInit{

  utenti?: Observable<Utente[]> = of([])

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {
    this.utenti = getUtenti(this.http_c)
  }

  onSubmit() {
    this.router.navigate(['/admin'])
  }
}
