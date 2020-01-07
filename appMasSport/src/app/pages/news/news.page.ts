import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NewsService } from '../../services/news/news.service';
import { map } from 'rxjs/operators';
import * as jsxml from 'xml-js';
import { LoadingService } from '../../services/loading/loading.service'
import { Router} from '@angular/router';

@Component({
  selector: 'app-news',
  templateUrl: './news.page.html',
  styleUrls: ['./news.page.scss'],
})
export class NewsPage implements OnInit, OnDestroy {

	data : any;
	info : any;
	infoUp : any;
	dataUp : any;
  test : any;
  tab = 0;
  backButtonSubscription: any;
  
  constructor(private  router: Router, private http: HttpClient, private news : NewsService, private loading : LoadingService) { }

  ngOnInit() {
    this.loading.presentLoading()
  	this.http.get(this.news.getNewsUpdate(),{responseType: 'text'})
      .subscribe(data => {
      	this.info = jsxml.xml2json(data, { compact: true, spaces: 0 });
        this.data = JSON.parse(this.info)
        this.infoUp = this.data.rss.channel.category._text
        this.dataUp = this.data.rss.channel.item
      },
      (error: any) => {
          console.log(error);
      });
  }

  viewInfo(i){
  this.loading.presentLoading()
  this.tab = 1
  this.test = this.dataUp[i]
  }
  
  prevPage(){
    this.loading.presentLoading()
    this.tab = 0
  }

  ngOnDestroy() {
    console.log("Exit")
  }

}
