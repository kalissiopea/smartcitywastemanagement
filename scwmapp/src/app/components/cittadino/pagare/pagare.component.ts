import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Pagamento} from "../../../models/pagamento";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {postPagamento} from "../../../logic/pagare_fs";
import {Tassa} from "../../../models/tassa";
import {getTasse} from "../../../logic/tasse_fs";
import {map, Observable, of, switchMap, toArray} from "rxjs";

@Component({
  selector: 'app-pagare',
  templateUrl: './pagare.component.html',
  styleUrls: ['./pagare.component.scss']
})
export class PagareComponent implements OnInit{

  pagamento: Pagamento={
      id: "",
      username: "",
      card: "",
      data: "",
      costo: 0,
      stato: true
  }

  tasse?: Observable<Tassa[]> = of([]);
  idTassa?: string
  importo?: number
  tassa?: Tassa

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit(): void {
      this.tasse = getTasse(this.http_c)
  }

 onSubmit(){
    postPagamento(this.http_c, this.pagamento)
    this.router.navigate(['/visualizzaTasse'])
 }

 onSubmit1(): void {
     this.router.navigate(['/citt'])
 }

  onSelectChange() {
    this.tasse?.subscribe((data: any[]) => {
      for (this.tassa of data) {
        if (this.idTassa === this.tassa?.id) {
          this.importo = this.tassa?.importo;
          this.pagamento.costo = this.importo;
          this.pagamento.id = this.idTassa;
        }
      }
    });
  }

}
