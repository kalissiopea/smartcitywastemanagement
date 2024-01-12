export const configuration = {

  //login con api gateway
  urlLogin: "https://866d5933tl.execute-api.us-east-1.amazonaws.com/staging/logs/login",
  urlReg: "https://866d5933tl.execute-api.us-east-1.amazonaws.com/staging/logs/reg",

  //admin
  urlAU: "http://52.54.199.145:8080/admin/utenti/registra",
  urlAC: "http://52.54.199.145:8080/admin/cassonetti/aggiungi",
  urlMC: "http://52.54.199.145:8080/admin/cassonetti/aggiornaStato",
  urlEC: "http://52.54.199.145:8080/admin/cassonetti/cancella",
  urlEU: "http://52.54.199.145:8080/admin/utenti/cancella",
  urlVC: "http://52.54.199.145:8080/admin/cassonetti/lista",
  urlVU: "http://52.54.199.145:8080/admin/utenti/lista",

  //cittadino
  //checking
  urlCheck: "http://34.193.105.215:8083/check/performance/mie",
  urlGiudizi: "http://34.193.105.215:8083/check/giudizio/mie",
  //pagare
  urlFBUtassa: "http://18.207.172.198:8082/cittadino/tassa/mie",
  urlAggiungiPagamento: "http://18.207.172.198:8082/cittadino/pagamenti/aggiungi",
  urlFBU: "http://18.207.172.198:8082/cittadino/pagamenti/mie",
  // urlCheck: "http://localhost:8083/check/performance/mie",
  // urlGiudizi: "http://localhost:8083/check/giudizio/mie",
  // //pagare
  // urlFBUtassa: "http://localhost:8082/cittadino/tassa/mie",
  // urlAggiungiPagamento: "http://18.207.172.198:8082/cittadino/pagamenti/aggiungi",
  // urlFBU: "http://localhost:8082/cittadino/pagamenti/mie",


  urlChecks: "urlCheckComunetasse",


  //manager monitoraggio
  urlGetAllarmi: "http://34.237.180.161:8084/allarme/lista",
  urlGetCassonetti: "http://34.237.180.161:8084/monitorare/cassonetti/lista",
  urlGetPercorsi: "http://34.237.180.161:8084/percorso/lista",
  urlAggiungiPercorso: "http://34.237.180.161:8084/percorso/aggiungi",

  //impiegato
  //giudizio
  urlGiudizio: "http://35.172.101.10:8085/giudizio/consulta/lista",
  urlAggiungi: "http://35.172.101.10:8085/giudizio/consulta/aggiungi",
  urlPerf: "http://35.172.101.10:8085/giudizio/check/lista",
  //emissione
  urlAggiungiTassa: "http://3.211.210.231:8086/cittadino/tassa/aggiungi",
  urlTutteTasse: "http://3.211.210.231:8086/cittadino/tassa/lista"

/*
  //URL IN LOCALE
  //login
  urlLogin: "http://localhost:8081/utenti/autenticazione",
  urlReg: "http://localhost:8081/utenti/registrazione",

  //admin
  urlAU: "http://localhost:8080/admin/utenti/registra",
  urlAC: "http://localhost:8080/admin/cassonetti/aggiungi",
  urlMC: "http://localhost:8080/admin/cassonetti/aggiornaStato",
  urlEC: "http://localhost:8080/admin/cassonetti/cancella",
  urlEU: "http://localhost:8080/admin/utenti/cancella",
  urlVC: "http://localhost:8080/admin/cassonetti/lista",
  urlVU: "http://localhost:8080/admin/utenti/lista",

  //cittadino
  //checking
  urlCheck: "http://localhost:8083/check/performance/mie",
  urlGiudizi: "http://localhost:8083/check/giudizio/mie",
  //pagare
  urlFBUtassa: "http://localhost:8082/cittadino/tassa/mie",
  urlAggiungiPagamento: "http://localhost:8082/cittadino/pagamenti/aggiungi",
  urlFBU: "http://localhost:8082/cittadino/pagamenti/mie",

  urlChecks: "urlCheckComunetasse",

  //manager monitoraggio
  urlGetAllarmi: "http://localhost:8084/allarme/lista",
  urlGetCassonetti: "http://localhost:8084/monitorare/cassonetti/lista",
  urlGetPercorsi: "http://localhost:8084/percorso/lista",
  urlAggiungiPercorso: "http://localhost:8084/percorso/aggiungi",

  //impiegato
  //giudizio
  urlGiudizio: "http://localhost:8085/giudizio/consulta/lista",
  urlAggiungi: "http://localhost:8085/giudizio/consulta/aggiungi",
  urlPerf: "http://localhost:8085/giudizio/check/lista",
  //emissione
  urlAggiungiTassa: "http://localhost:8086/cittadino/tassa/aggiungi",
  urlTutteTasse: "http://localhost:8086/cittadino/tassa/lista"
*/
}
