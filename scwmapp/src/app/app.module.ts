import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IntroComponent } from './components/intro/intro.component';
import { RegComponent } from './components/reg/reg.component';
import { LoginComponent } from './components/login/login.component';
import { ComuneComponent } from './components/comune/comune.component';
import { CittadinoComponent } from './components/cittadino/cittadino.component';
import { AdminComponent } from './components/admin/admin.component';
import { CheckingComponent } from './components/checking/checking.component';
import { GiudizioComponent } from './components/giudizio/giudizio.component';
import { AgguComponent } from './components/admin/aggu/aggu.component';
import { AggcComponent } from './components/admin/aggc/aggc.component';
// import { ModcComponent } from './components/admin/modc/modc.component';
// import { ElimuComponent } from './components/admin/elimu/elimu.component';
// import { ElimcComponent } from './components/admin/elimc/elimc.component';
import { VisuComponent } from './components/admin/visu/visu.component';
import { ViscComponent } from './components/admin/visc/visc.component';
import { GiudiziComponent } from './components/checking/giudizi/giudizi.component';
import { AggiungiComponent } from './components/giudizio/aggiungi/aggiungi.component';
import { PerformanceComponent } from './components/giudizio/performance/performance.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {
  IstanziaPercorsoPuliziaComponent
} from "./components/aziendarifiuti/istanzia-percorso-pulizia/istanzia-percorso-pulizia.component";
import {VisualizzaAllarmiComponent} from "./components/aziendarifiuti/visualizza-allarmi/visualizza-allarmi.component";
import {
  MonitoraCassonettiComponent
} from "./components/aziendarifiuti/monitora-cassonetti/monitora-cassonetti.component";
import {AziendarifiutiComponent} from "./components/aziendarifiuti/aziendarifiuti.component";
import {VisualizzaTasseComponent} from "./components/cittadino/visualizza-tasse/visualizza-tasse.component";
import {PagareComponent} from "./components/cittadino/pagare/pagare.component";
import {VisualizzaPagamentiComponent} from "./components/cittadino/visualizza-pagamenti/visualizza-pagamenti.component";
import {EmettiTassaComponent} from "./components/comune/emetti-tassa/emetti-tassa.component";
import { VisualizzaPercorsiComponent } from './components/aziendarifiuti/visualizza-percorsi/visualizza-percorsi.component';
import { VediTasseComponent } from './components/comune/vedi-tasse/vedi-tasse.component';

@NgModule({
  declarations: [
    AppComponent,
    IntroComponent,
    RegComponent,
    LoginComponent,
    ComuneComponent,
    CittadinoComponent,
    AdminComponent,
    CheckingComponent,
    GiudizioComponent,
    AgguComponent,
    AggcComponent,
    // ModcComponent,
    // ElimuComponent,
    // ElimcComponent,
    VisuComponent,
    ViscComponent,
    GiudiziComponent,
    AggiungiComponent,
    PerformanceComponent,
    IstanziaPercorsoPuliziaComponent,
    VisualizzaAllarmiComponent,
    MonitoraCassonettiComponent,
    AziendarifiutiComponent,
    VisualizzaTasseComponent,
    PagareComponent,
    VisualizzaPagamentiComponent,
    EmettiTassaComponent,
    VisualizzaPercorsiComponent,
    VediTasseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
