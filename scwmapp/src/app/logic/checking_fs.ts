import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Utente} from "../models/utente";
import {configuration} from "../config/configuration";
import {Checking} from "../models/checking";
import {Giudizio} from "../models/giudizio";

function build_headers(){

    return {
        headers : new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization' : 'bearer ' + localStorage.getItem("jwt")
        })
    }
}

export function getChecking(http_client : HttpClient){

    const headers = build_headers()
    const url : string = configuration.urlCheck

    const returned = http_client.get<Checking>(url, headers)
    //const post = http_client.get<Post>(url, headers)
    return returned;
}

export function getGiudizi(http_client : HttpClient){

    const headers = build_headers()
    const url : string = configuration.urlGiudizi

    const returned = http_client.get<Giudizio[]>(url, headers)
    return returned;
}

//check del comune per emettere le tasse
export function getCheckings(http_client : HttpClient){

  const headers = build_headers()
  const url = configuration.urlChecks

  const returned = http_client.get<Checking[]>(url, headers)

  return returned;
}
