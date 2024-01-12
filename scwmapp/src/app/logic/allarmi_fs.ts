import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Allarme} from "../models/allarme";
import {configuration} from "../config/configuration";

function build_headers(): {headers: HttpHeaders} {
    return {
        headers : new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'bearer ' + localStorage.getItem("jwt")
        })
    }
}

export function getAllarmi(http_client : HttpClient){

    const headers = build_headers()
    const url = configuration.urlGetAllarmi

    const returned = http_client.get<Allarme[]>(url, headers)

    return returned;
}
