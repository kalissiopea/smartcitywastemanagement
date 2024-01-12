import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {IntroComponent} from "./components/intro/intro.component";
import {RegComponent} from "./components/reg/reg.component";
import {LoginComponent} from "./components/login/login.component";
import {AdminComponent} from "./components/admin/admin.component";
import {AgguComponent} from "./components/admin/aggu/aggu.component";
import {AggcComponent} from "./components/admin/aggc/aggc.component";
// import {ElimuComponent} from "./components/admin/elimu/elimu.component";
// import {ElimcComponent} from "./components/admin/elimc/elimc.component";
import {VisuComponent} from "./components/admin/visu/visu.component";
// import {ModcComponent} from "./components/admin/modc/modc.component";
import {CittadinoComponent} from "./components/cittadino/cittadino.component";
import {ComuneComponent} from "./components/comune/comune.component";
import {CheckingComponent} from "./components/checking/checking.component";
import {GiudiziComponent} from "./components/checking/giudizi/giudizi.component";
import {GiudizioComponent} from "./components/giudizio/giudizio.component";
import {PerformanceComponent} from "./components/giudizio/performance/performance.component";
import {AggiungiComponent} from "./components/giudizio/aggiungi/aggiungi.component";
import {ViscComponent} from "./components/admin/visc/visc.component";
import {AziendarifiutiComponent} from "./components/aziendarifiuti/aziendarifiuti.component";
import {
  MonitoraCassonettiComponent
} from "./components/aziendarifiuti/monitora-cassonetti/monitora-cassonetti.component";
import {
  IstanziaPercorsoPuliziaComponent
} from "./components/aziendarifiuti/istanzia-percorso-pulizia/istanzia-percorso-pulizia.component";
import {VisualizzaAllarmiComponent} from "./components/aziendarifiuti/visualizza-allarmi/visualizza-allarmi.component";
import {VisualizzaTasseComponent} from "./components/cittadino/visualizza-tasse/visualizza-tasse.component";
import {PagareComponent} from "./components/cittadino/pagare/pagare.component";
import {VisualizzaPagamentiComponent} from "./components/cittadino/visualizza-pagamenti/visualizza-pagamenti.component";
import {EmettiTassaComponent} from "./components/comune/emetti-tassa/emetti-tassa.component";
import {
  VisualizzaPercorsiComponent
} from "./components/aziendarifiuti/visualizza-percorsi/visualizza-percorsi.component";
import {VediTasseComponent} from "./components/comune/vedi-tasse/vedi-tasse.component";

const routes: Routes = [
  {path: "intro", component: IntroComponent},
  {path: "reg", component: RegComponent},
  {path: "login", component: LoginComponent},
  {path: "admin", component: AdminComponent},
  {path: "aggu", component: AgguComponent},
  {path: "aggc", component: AggcComponent},
  // {path: "elimu", component: ElimuComponent},
  // {path: "elimc", component: ElimcComponent},
  {path: "visu", component: VisuComponent},
  {path: "visc", component: ViscComponent},
  // {path: "modc", component: ModcComponent},
  {path: "citt", component: CittadinoComponent},
  {path: "comune", component: ComuneComponent},
  {path: "checking", component: CheckingComponent},
  {path: "giudizi", component: GiudiziComponent},
  {path: "giudizio", component: GiudizioComponent},
  {path: "performance", component: PerformanceComponent},
  {path: "aggiungi", component: AggiungiComponent},
  {path:"pagare",component: PagareComponent},
  {path:"homeaziendarifiuti",component: AziendarifiutiComponent},
  {path:"monitoraCassonetti",component: MonitoraCassonettiComponent},
  {path:"istanziaPercorsoPulizia",component: IstanziaPercorsoPuliziaComponent},
  {path:"visualizzaAllarmi",component: VisualizzaAllarmiComponent},
  {path:"emettiTassa",component: EmettiTassaComponent},
  {path:"visualizzaTasse",component: VisualizzaTasseComponent},
  {path:"visualizzaPagamenti",component: VisualizzaPagamentiComponent},
  {path:"visPercorsi",component: VisualizzaPercorsiComponent},
  {path:"vediTax",component: VediTasseComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
