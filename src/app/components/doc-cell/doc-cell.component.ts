import { Component, Input, OnInit } from '@angular/core';
import { DocModel } from 'src/app/models/doc-model';

@Component({
  selector: 'app-doc-cell',
  templateUrl: './doc-cell.component.html',
  styleUrls: ['./doc-cell.component.scss']
})
export class DocCellComponent implements OnInit {

  @Input()
  doc!: DocModel;

  constructor() { }

  ngOnInit(): void {
  }

}
