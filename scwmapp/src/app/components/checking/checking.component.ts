import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Checking} from "../../models/checking";
import {getChecking} from "../../logic/checking_fs";
import {Observable} from "rxjs";

@Component({
  selector: 'app-checking',
  templateUrl: './checking.component.html',
  styleUrls: ['./checking.component.scss']
})
export class CheckingComponent  implements OnInit{

  checking?: Observable<Checking>

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {
    this.checking = getChecking(this.http_c)
  }

  onSubmit() {
    this.router.navigate(['/citt'])
  }

}
