import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-intro',
  templateUrl: './intro.component.html',
  styleUrls: ['./intro.component.scss']
})
export class IntroComponent implements OnInit{

  constructor(private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {

  }

  onSubmitL() {
    this.router.navigate(["/login"])
  }

  onSubmitR() {
    this.router.navigate(["/reg"])
  }
}
