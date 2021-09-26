import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { DocModel } from 'src/app/models/doc-model';
import { DocsService } from 'src/app/services/docs.service';

@Component({
  selector: 'app-doc',
  templateUrl: './doc.component.html',
  styleUrls: ['./doc.component.scss']
})
export class DocComponent implements OnInit {

  doc!:DocModel;

  content = '';
  docIdParam!:string;
  docTitle!:string;


  constructor(private route:ActivatedRoute,
    private docService:DocsService,
    private router:Router) {
      this.route.params.subscribe(
        params=> {
          if(params.id){
            this.docIdParam = params.id;
          }
        }
      )

     }

  ngOnInit(): void {

    if(this.docIdParam){
      this.fetchDocument();
      console.log(this.content);
    }

  }


  fetchDocument(){

    this.docService.fetchDoc(this.docIdParam)
    .subscribe(
      (      data: DocModel) =>{
          this.doc = data;
          this.content = this.doc.content;   
          console.log(this.content);

      },

      (      error: any)=>{
          alert(`Couldn't fetch doc ${this.docIdParam}: ${error}`)
      }

    )

  }

  loadSuccessful(event: any){
      console.log(`load succ ${event}`)
      console.log(this.content);
  }

  onError(event: any){
    alert(`Couldn't fetch doc ${this.docIdParam}: ${event}`)
  }


  saveDoc(){
    this.doc.content = this.content;
    this.doc.updatedAt = '';
    this.doc.title=this.docTitle;


    this.docService.saveDoc(this.doc)
    .subscribe(
      data => {
          this.router.navigate(['/mydocs'])
          
      },
      error => {

        alert(`Doc couldn't be saved:  ${error}`)

      }
    )

  }


}
