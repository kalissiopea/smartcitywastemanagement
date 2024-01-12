import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Cassonetto} from "../models/cassonetto";
import {configuration} from "../config/configuration";

function build_headers(): {headers: HttpHeaders} {
    return {
        headers : new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'bearer ' + localStorage.getItem("jwt")
        })
    }
}

export function getCassonetti(http_client : HttpClient){

    const headers = build_headers()
    const url = configuration.urlGetCassonetti

    const returned = http_client.get<Cassonetto[]>(url, headers)

    return returned;
}
