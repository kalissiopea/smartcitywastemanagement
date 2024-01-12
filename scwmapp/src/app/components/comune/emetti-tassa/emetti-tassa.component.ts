import { Component } from '@angular/core';
import {Tassa} from "../../../models/tassa";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {postTassa} from "../../../logic/tasse_fs";

@Component({
  selector: 'app-emetti-tassa',
  templateUrl: './emetti-tassa.component.html',
  styleUrls: ['./emetti-tassa.component.scss']
})
export class EmettiTassaComponent {


  tassa: Tassa={
    username: "",
    data: ""
  }

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  onSubmit(){
    postTassa(this.http_c, this.tassa)
    this.router.navigate(['/comune'])
  }
  onSubmit1(){
    this.router.navigate(['/comune'])
  }

}
