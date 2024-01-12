import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {postCred} from "../../logic/login_fs";
import {HttpClient} from "@angular/common/http";
import {Login} from "../../models/login";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{

  public showPassword = false;

  login: Login = {
    username:'',
    password:''
  }

  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {

  }


  onSubmit() {
    const map = postCred(this.http_c, this.login)
    map.subscribe((data) => {
      const ruolo = data.get("ruolo");
      console.log(ruolo)
      if (ruolo == "cittadino") {
        this.router.navigate(["/citt"])
      } else if (ruolo == "amministratore") {
        this.router.navigate(["/admin"])
      } else if (ruolo == "impiegato") {
        this.router.navigate(["/comune"])
      } else if (ruolo == "manager azienda rifiuti") {
        this.router.navigate(["/homeaziendarifiuti"])
      }
    });
  }

}
