import { Component } from '@angular/core';
import {Observable, of} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Pagamento} from "../../../models/pagamento";
import {getPagamenti, postPagamento} from "../../../logic/pagare_fs";

@Component({
  selector: 'app-visualizza-pagamenti',
  templateUrl: './visualizza-pagamenti.component.html',
  styleUrls: ['./visualizza-pagamenti.component.scss']
})
export class VisualizzaPagamentiComponent {


  pagamenti?: Observable<Pagamento[]> = of([]);

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit(): void {
    this.pagamenti = getPagamenti(this.http_c)
  }

  onSubmit0(){
    this.router.navigate(['/citt'])
  }

  onSubmit1(){
    this.router.navigate(['/visualizzaTasse'])
  }

  onSubmit2(){
    this.router.navigate(['/pagare'])
  }
}
