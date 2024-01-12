// import {Component, OnInit} from '@angular/core';
// import {Cassonetto} from "../../../models/cassonetto";
// import {ActivatedRoute, Router} from "@angular/router";
// import {HttpClient} from "@angular/common/http";
// import {deleteCassonetto, deleteUtente} from "../../../logic/admin_fs";
// import {Utente} from "../../../models/utente";
//
// @Component({
//   selector: 'app-elimu',
//   templateUrl: './elimu.component.html',
//   styleUrls: ['./elimu.component.scss']
// })
// export class ElimuComponent implements OnInit{
//
//   utente: Utente = {
//     id:'',
//     nome:'',
//     cognome:'',
//     email:'',
//     eta:0,
//     username:'',
//     password:'',
//     ruolo:'',
//     indirizzo:'',
//     card:''
//   }
//
//   constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
//   }
//
//   ngOnInit() {
//
//   }
//
//   onSubmit() {
//     deleteUtente(this.http_c, this.utente);
//     this.router.navigate(['/admin'])
//   }
//
// }
