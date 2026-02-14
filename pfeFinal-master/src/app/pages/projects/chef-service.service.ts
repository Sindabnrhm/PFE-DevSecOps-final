import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const AUTH_API = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class ChefServiceService {

 
  constructor(private http: HttpClient) { }

  

  getDemandeChef=(serv:any):Observable<any[]>=>{
    return this.http.get<any[]>(AUTH_API + "conge/getDemandeChef/"+serv);

  }
  getDemandeChefNotNull=(serv:any):Observable<any[]>=>{
    return this.http.get<any[]>(AUTH_API + "conge/getDemandeChefNotNull/"+serv);

  }
  UpdateDemande(data:any):Observable<any>{
    return this.http.put<any>(AUTH_API+"conge/updateChef",data);
  }
}
