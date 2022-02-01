import { Component, OnInit } from '@angular/core';
import { DocModel } from 'src/app/models/doc-model';
import { DocsService } from 'src/app/services/docs.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  recentDocs:DocModel[] =[];

  constructor( private docService: DocsService) { 

  }

  ngOnInit(): void {

    this.fetchRecentDocs();

  }

  reloadRecentDocs(){
    this.fetchRecentDocs();
  }


  fetchRecentDocs(){
    this.docService.fetchRecentDocuments()
    .subscribe(
      (data: DocModel[])=> {
        this.recentDocs = data;  
      },
      (error: { message: any; })=>{
        alert(`Recent docs could not be fetched: ${error.message}`);
      }
    )
  }


}
