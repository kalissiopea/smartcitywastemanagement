import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {logout} from "./logic/login_fs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'scwmapp';

    constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
    }

    ngOnInit() {

    }


    onSubmit() {
      logout();
      this.router.navigate(['/intro'])
    }
}
