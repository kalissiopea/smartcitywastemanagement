import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Tassa} from "../models/tassa";
import {configuration} from "../config/configuration";

function build_headers(): {headers: HttpHeaders} {
    return {
      headers : new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'bearer ' + localStorage.getItem("jwt")
      })
    }
}

export function getTasse(http_client : HttpClient){

  const headers = build_headers()
    const url = configuration.urlFBUtassa

    const returned = http_client.get<Tassa[]>(url, headers)

    return returned;
}
export function postTassa(http_client : HttpClient, tassa : Tassa){

    const headers = build_headers()
    const url = configuration.urlAggiungiTassa

    const returned = http_client.post<Tassa>(url, JSON.stringify(tassa), headers)

    returned.subscribe(res => {
        console.log(res)
    })
}

export function getTutte(http_client : HttpClient){

  const headers = build_headers()
  const url = configuration.urlTutteTasse

  const returned = http_client.get<Tassa[]>(url, headers)

  return returned;
}
