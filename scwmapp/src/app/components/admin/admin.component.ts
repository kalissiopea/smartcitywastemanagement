import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit{
  constructor(private route: ActivatedRoute, private router: Router, private http_c: HttpClient) {
  }

  ngOnInit() {

  }

  onSubmitAU(){
    this.router.navigate(['/aggu'])
  }

  onSubmitAC(){
    this.router.navigate(['/aggc'])
  }

  // onSubmitMC(){
  //   this.router.navigate(['/modc'])
  // }
  //
  // onSubmitEC(){
  //   this.router.navigate(['/elimc'])
  // }
  //
  // onSubmitEU(){
  //   this.router.navigate(['/elimu'])
  // }

  onSubmitVU(){
    this.router.navigate(['/visu'])
  }

  onSubmitVC(){
    this.router.navigate(['/visc'])
  }
}
