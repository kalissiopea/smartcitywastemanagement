import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Pagamento} from "../models/pagamento";
import {configuration} from "../config/configuration";

function build_headers(): {headers: HttpHeaders} {
    return {
      headers : new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'bearer ' + localStorage.getItem("jwt")
      })
    }
}

export function getPagamenti(http_client : HttpClient){

    const headers = build_headers()
    const url = configuration.urlFBU

    const returned = http_client.get<Pagamento[]>(url, headers)

    return returned;
}

export function postPagamento(http_client : HttpClient, pagamento : Pagamento){

  const headers = build_headers()
    const url = configuration.urlAggiungiPagamento

    const returned = http_client.post<Pagamento>(url, JSON.stringify(pagamento), headers)

    returned.subscribe(res => {
      console.log(res)
    })
}
