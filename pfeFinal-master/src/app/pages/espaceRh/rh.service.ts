import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const AUTH_API = 'http://localhost:8080/';
@Injectable({
  providedIn: 'root'
})
export class RhService {

 
  constructor(private http: HttpClient) { }

  getService=():Observable<any[]>=>{
    return this.http.get<any[]>(AUTH_API + "service/getService");

  }
  getTypeConge=():Observable<any[]>=>{
    return this.http.get<any[]>(AUTH_API + "service/getTypeConge");

  }

  getDemandeRh=():Observable<any[]>=>{
    return this.http.get<any[]>(AUTH_API + "conge/getDemandeRh");

  }
  getDemandeRhNotNull=():Observable<any[]>=>{
    return this.http.get<any[]>(AUTH_API + "conge/getDemandeRhNotNull");

  }
  addService(data:any):Observable<any>{
    return this.http.post<any>(AUTH_API+"service/addService",data);
  }
  addTypeConge(data:any):Observable<any>{
    return this.http.post<any>(AUTH_API+"service/addTypeConge",data);
  }

  UpdateDemandeRh(data:any):Observable<any>{
    return this.http.put<any>(AUTH_API+"conge/updateRh",data);
  }
  GetUser = (): Observable<any[]> => {
    return this.http.get<any[]>(AUTH_API + "api/auth/getUsers");
  };
  UpdateRole(data:any):Observable<any>{
    return this.http.put<any>(AUTH_API+"api/auth/setRole",data);
  }
  UpdatePss(data:any):Observable<any>{
    return this.http.put<any>(AUTH_API+"api/auth/updatePass",data);
  }
  Updatepers(data:any):Observable<any>{
    return this.http.post<any>(AUTH_API+"api/auth/add",data);
  }
  DeleteService = (id:any): Observable<any[]> => {
    return this.http.delete<any[]>(AUTH_API + "service/service/"+id);
  }; 
  DeleteType = (id:any): Observable<any[]> => {
    return this.http.delete<any[]>(AUTH_API + "service/type/"+id);
  }; 
}
