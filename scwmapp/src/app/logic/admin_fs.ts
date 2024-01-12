import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Utente} from "../models/utente";
import {configuration} from "../config/configuration";
import {Cassonetto} from "../models/cassonetto";
import {Checking} from "../models/checking";

function build_headers(){

    return {
        headers : new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization' : 'bearer ' + localStorage.getItem("jwt"),
            // 'Access-Control-Allow-Origin' : '*'
        })
    }
}

export function postUser(http_client : HttpClient, utente : Utente){

    const headers = build_headers()
    const url = configuration.urlAU


    const returned = http_client.post<Utente>(url, JSON.stringify(utente), headers)
    //http_client.post<Post>(url, {post}, headers)

    returned.subscribe(res => {
        console.log(res)
    })
}

export function postCassonetto(http_client : HttpClient, cassonetto : Cassonetto){

    const headers = build_headers()
    const url = configuration.urlAC


    const returned = http_client.post<Cassonetto>(url, JSON.stringify(cassonetto), headers)
    //http_client.post<Post>(url, {post}, headers)

    returned.subscribe(res => {
        console.log(res)
    })
}

// export function putCassonetto(http_client : HttpClient, cassonetto : Cassonetto){
//
//     const headers = build_headers()
//     const url = configuration.urlMC + '/' + cassonetto.luogo + '/' + cassonetto.stato
//
//
//     const returned = http_client.put<Cassonetto>(url, headers)
//     //http_client.post<Post>(url, {post}, headers)
//
//     returned.subscribe(res => {
//         console.log(res)
//     })
// }

// export function deleteCassonetto(http_client : HttpClient, cassonetto : Cassonetto){
//
//     const headers = build_headers()
//     const url = configuration.urlEC + '/' + cassonetto.luogo
//
//
//     const returned = http_client.delete<Cassonetto>(url, headers)
//     //http_client.post<Post>(url, {post}, headers)
//
//     returned.subscribe(res => {
//         console.log(res)
//     })
// }
//
// export function deleteUtente(http_client : HttpClient, utente : Utente){
//
//     const headers = build_headers()
//     const url = configuration.urlEU + '/' + utente.username
//
//
//     const returned = http_client.delete<Utente>(url, headers)
//     //http_client.post<Post>(url, {post}, headers)
//
//     returned.subscribe(res => {
//         console.log(res)
//     })
// }

export function getUtenti(http_client : HttpClient){

    const headers = build_headers()
    const url : string = configuration.urlVU

    const returned = http_client.get<Utente[]>(url, headers)
    //const post = http_client.get<Post>(url, headers)
    return returned;
}

export function getCassonetti(http_client : HttpClient){

    const headers = build_headers()
    const url : string = configuration.urlVC

    const returned = http_client.get<Cassonetto[]>(url, headers)
    //const post = http_client.get<Post>(url, headers)
    return returned;
}

