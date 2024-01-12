import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-aziendarifiuti',
  templateUrl: './aziendarifiuti.component.html',
  styleUrls: ['./aziendarifiuti.component.scss']
})
export class AziendarifiutiComponent {

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }
  onSubmit0(){
    this.router.navigate(['/monitoraCassonetti'])
  }

  onSubmit1(){
    this.router.navigate(['/istanziaPercorsoPulizia'])
  }

  onSubmit2(){
    this.router.navigate(['/visualizzaAllarmi'])
  }

  onSubmit3(){
    this.router.navigate(['/visPercorsi'])
  }


}
