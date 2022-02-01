import { Component, OnInit } from '@angular/core';
import { DocModel } from 'src/app/models/doc-model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { DocsService } from 'src/app/services/docs.service';

@Component({
  selector: 'app-mydocs',
  templateUrl: './mydocs.component.html',
  styleUrls: ['./mydocs.component.scss']
})
export class MydocsComponent implements OnInit {


  myDocList: DocModel[] = [];

  constructor(private docService:DocsService,
    private authenticationService:AuthenticationService) { }

  ngOnInit(): void {

  // if (this.authenticationService.currentUserValue 
  // && this.authenticationService.currentUserValue.id){
    this.docService.getMyDocs(this.authenticationService.currentUserValue.id)
    .subscribe(
      data => {
        console.log(data);
        this.myDocList = data;
      },
      error=>{
        alert(`{error}`)
      }
    );

  //}

 
  }


  cellDeleted(event:any){
    
    this.myDocList = this.myDocList.filter(doc => doc.id !== event)
  }


}
