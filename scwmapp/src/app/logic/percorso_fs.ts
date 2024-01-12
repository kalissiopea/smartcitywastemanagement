import {HttpClient, HttpHeaders} from "@angular/common/http";
import {configuration} from "../config/configuration";
import {Percorso} from "../models/percorso";
import {Allarme} from "../models/allarme";

function build_headers(): {headers: HttpHeaders} {
    return {
        headers : new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'bearer ' + localStorage.getItem("jwt")
        })
    }
}

export function postPercorso(http_client : HttpClient, percorso: Percorso){

    const headers = build_headers()
    const url = configuration.urlAggiungiPercorso

    const returned = http_client.post<Percorso>(url, JSON.stringify(percorso), headers)

    returned.subscribe(res => {
        console.log(res)
    })
}

export function getPercorso(http_client : HttpClient){

  const headers = build_headers()
  const url = configuration.urlGetPercorsi

  const returned = http_client.get<Percorso[]>(url, headers)

  return returned;
}
