import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { DocModel } from 'src/app/models/doc-model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { DocsService } from 'src/app/services/docs.service';

@Component({
  selector: 'app-doc-cell',
  templateUrl: './doc-cell.component.html',
  styleUrls: ['./doc-cell.component.scss'],
  inputs:['doc']
})
export class DocCellComponent implements OnInit {

  @Input()
  doc!: DocModel;


  @Output()
  docDeleted:EventEmitter<string> = new EventEmitter<string>();
  constructor(private authenticationService:AuthenticationService,
    private docService:DocsService,
    private router:Router) { }

  ngOnInit(): void {
  }

  deleteDocument(event:any){

   // alert(`Doc {this.doc.id} was deleted`);
    // call delete document endpoint
    this.docService.deleteDocument(this.doc.id)
    .subscribe(

      data => {
              // if successful 
       // notify the parent to not render this document
       this.docDeleted.emit(this.doc.id);
      },
      error =>{
          alert(`Doc ${this.doc.id} was not deleted`)
      }

    )



  }

  currentUserIsOwner(){

    if(this.doc === null || this.doc.id === null || 
      this.authenticationService.currentUserValue===null){
      return false;
    }

    // compare the current user Id with the doc userId
    const docOwner = this.doc.userId;
    const currentUserId = this.authenticationService.currentUserValue.id;

    return docOwner === currentUserId;

  }

cellClicked(){
  if(this.doc && this.doc.id)
  {
    this.router.navigate(['doc',this.doc.id]);
  }
}

 


}
