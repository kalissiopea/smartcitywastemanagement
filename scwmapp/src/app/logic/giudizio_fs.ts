import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Utente} from "../models/utente";
import {configuration} from "../config/configuration";
import {Checking} from "../models/checking";
import {Giudizio} from "../models/giudizio";
import {GiudizioComponent} from "../components/giudizio/giudizio.component";

function build_headers(){

    return {
        headers : new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization' : 'bearer ' + localStorage.getItem("jwt")
        })
    }
}

export function getPerformance(http_client : HttpClient){

    const headers = build_headers()
    const url : string = configuration.urlPerf

    const returned = http_client.get<Checking[]>(url, headers)
    //const post = http_client.get<Post>(url, headers)
    return returned;
}

export function getGiudizio(http_client : HttpClient){

    const headers = build_headers()
    const url : string = configuration.urlGiudizio

    const returned = http_client.get<Giudizio[]>(url, headers)
    return returned;
}

export function postGiudizio(http_client : HttpClient, giudizio: Giudizio){

    const headers = build_headers()
    const url : string = configuration.urlAggiungi

    const returned = http_client.post<Giudizio>(url, JSON.stringify(giudizio), headers)
    returned.subscribe(res => {
      console.log(res)
    })
}
