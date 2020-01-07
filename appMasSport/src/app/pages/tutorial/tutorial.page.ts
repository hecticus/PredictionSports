import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router} from '@angular/router';

@Component({
  selector: 'app-tutorial',
  templateUrl: './tutorial.page.html',
  styleUrls: ['./tutorial.page.scss'],
})
export class TutorialPage implements OnInit, OnDestroy {

  slides = [
    {
      img: 'assets/img/tutorial/es/0.png'
    },
    {
      img: 'assets/img/tutorial/es/1.png'
    },
    {
      img: 'assets/img/tutorial/es/2.png'
    },
    {
      img: 'assets/img/tutorial/es/3.png'
    }
  ];
  constructor( private router : Router) { }

  ngOnInit() {
  }

  tabPredictions(){
    this.router.navigateByUrl('/tabs/predictions'); 
  }

  ngOnDestroy() {
    console.log("Exit")
  }
}
