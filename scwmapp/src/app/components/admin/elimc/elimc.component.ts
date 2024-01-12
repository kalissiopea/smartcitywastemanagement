// import {Component, OnInit} from '@angular/core';
// import {Cassonetto} from "../../../models/cassonetto";
// import {ActivatedRoute, Router} from "@angular/router";
// import {HttpClient} from "@angular/common/http";
// import {deleteCassonetto, putCassonetto} from "../../../logic/admin_fs";
//
// @Component({
//   selector: 'app-elimc',
//   templateUrl: './elimc.component.html',
//   styleUrls: ['./elimc.component.scss']
// })
// export class ElimcComponent implements OnInit{
//
//   cassonetto: Cassonetto = {
//     id:'',
//     luogo:'',
//     tipo:'',
//     stato:0,
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
//     deleteCassonetto(this.http_c, this.cassonetto);
//     this.router.navigate(['/admin'])
//   }
//
// }
