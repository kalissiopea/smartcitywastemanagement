import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-comune',
  templateUrl: './comune.component.html',
  styleUrls: ['./comune.component.scss']
})
export class ComuneComponent implements OnInit{
  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {

  }

  onSubmitP(){
    this.router.navigate(['/performance'])
  }

  onSubmitG(){
    this.router.navigate(['/giudizio'])
  }

  onSubmitV(){
    this.router.navigate(['/vediTax'])
  }

  onSubmitT(){
    this.router.navigate(['/emettiTassa'])
  }

  onSubmitE(){
    this.router.navigate(['/aggiungi'])
  }

}

