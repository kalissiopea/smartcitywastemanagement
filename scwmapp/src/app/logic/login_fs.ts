import {HttpClient, HttpHeaders} from "@angular/common/http";
import {configuration} from "../config/configuration";
import {Login} from "../models/login";
import {map, Observable} from "rxjs";

function build_headers(){

    return {
        headers : new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization' : 'bearer '
        })
    }
}

export function postCred(http_client: HttpClient, login: Login): Observable<Map<string, any>> {

  const headers = build_headers();
  const url = configuration.urlLogin;

  return http_client.post(url, JSON.stringify(login), headers).pipe(
    map(res => {
      const myMap = new Map(Object.entries(res));
      localStorage.setItem("jwt", JSON.stringify(myMap.get("jwt")));
      return myMap;
    })
  );
}

export function logout(){
    localStorage.removeItem('jwt')
    //location.reload() per ricaricare la pagina se serve
}

export function get_token(){
    const data_stored = localStorage.getItem('jwt')
}
