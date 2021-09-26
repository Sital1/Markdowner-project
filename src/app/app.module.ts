// import { NgModule } from '@angular/core';
// import { BrowserModule } from '@angular/platform-browser';
// import { MarkdownModule } from 'ngx-markdown';

// import { AppRoutingModule } from './app-routing.module';
// import { AppComponent } from './app.component';
// import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// import { HeaderComponent } from './components/header/header.component';

// import { FlexLayoutModule } from '@angular/flex-layout';



// import { MatSidenavModule } from  '@angular/material/sidenav';
// import { MatToolbarModule } from  '@angular/material/toolbar';
// import { MatButtonModule } from  '@angular/material/button';
// import { MatIconModule } from  '@angular/material/icon';
// import { MatListModule } from  '@angular/material/list';
// import { HomeComponent } from './components/home/home.component';
// import { LoginComponent } from './components/login/login.component';
// import { MydocsComponent } from './components/mydocs/mydocs.component';
// import { SidenavComponent } from './components/sidenav/sidenav.component';


// @NgModule({
//   declarations: [
//     AppComponent,
//     HeaderComponent,
//     HomeComponent,
//     LoginComponent,
//     MydocsComponent,
//     SidenavComponent
//   ],
//   imports: [
//     BrowserModule,
//     AppRoutingModule,
//     MarkdownModule.forRoot(),
//     BrowserAnimationsModule,
//     MatToolbarModule,
//     MatSidenavModule,
//     FlexLayoutModule,
//     MatButtonModule,
//     MatIconModule,
//     MatListModule
//   ],
//   providers: [],
//   bootstrap: [AppComponent]
// })
// export class AppModule { }

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MarkdownModule } from 'ngx-markdown';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { DocCellComponent } from './components/doc-cell/doc-cell.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { CookieService } from 'ngx-cookie-service';
import { MydocsComponent } from './components/mydocs/mydocs.component';
import { DocComponent } from './components/doc/doc.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
  
    SidenavComponent,
    DocCellComponent,
    RegisterComponent,
    LoginComponent,
    MydocsComponent,
    DocComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MarkdownModule.forRoot(),
    HttpClientModule,
    // * MATERIAL IMPORTS
    MatSidenavModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatDividerModule,
    MatListModule,
    FormsModule
   
  ],
  providers: [CookieService,MarkdownModule],
  bootstrap: [AppComponent],
})
export class AppModule {}