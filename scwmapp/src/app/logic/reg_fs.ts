import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Utente} from "../models/utente";
import {configuration} from "../config/configuration";

function build_headers(){

    return {
        headers : new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization' : 'bearer '
        })
    }
}

export function postUtente(http_client : HttpClient, utente : Utente){

    const headers = build_headers()
    const url = configuration.urlReg


    const returned = http_client.post<Utente>(url, JSON.stringify(utente), headers)
    //http_client.post<Post>(url, {post}, headers)

    returned.subscribe(res => {
        console.log(res)
    })
}
