// import {Component, OnInit} from '@angular/core';
// import {Cassonetto} from "../../../models/cassonetto";
// import {ActivatedRoute, Router} from "@angular/router";
// import {HttpClient} from "@angular/common/http";
// import {postCassonetto, putCassonetto} from "../../../logic/admin_fs";
//
// @Component({
//   selector: 'app-modc',
//   templateUrl: './modc.component.html',
//   styleUrls: ['./modc.component.scss']
// })
// export class ModcComponent implements OnInit{
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
//     putCassonetto(this.http_c, this.cassonetto);
//     this.router.navigate(['/admin'])
//   }
//
// }
