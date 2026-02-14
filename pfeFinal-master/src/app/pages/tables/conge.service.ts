import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class CongeService {

  constructor(private http: HttpClient) { }
  addConge(body:any){
    return this.http.post(AUTH_API + 'conge/add',body)

  }
  addSanction(body:any){
    return this.http.post(AUTH_API + 'conge/addSanction',body)

  }
  getMaxSolde=(mat:any):Observable<any[]>=>{
    return this.http.get<any[]>(AUTH_API + "conge/getMaxSolde/"+mat);

  }
  GetTypeConge = (): Observable<any[]> => {
    return this.http.get<any[]>(AUTH_API + "conge/getTypeConge");
  };
  GetCongeByMat = (mat:any): Observable<any[]> => {
    return this.http.get<any[]>(AUTH_API + "conge/get/"+mat);
  };
  GetRepChef = (mat:any,id:any): Observable<any[]> => {
    return this.http.get<any[]>(AUTH_API + "conge/getRepChef/"+mat+"/"+id);
  };
  DeleteCng = (id:any): Observable<any[]> => {
    return this.http.delete<any[]>(AUTH_API + "conge/"+id);
  }; 
}
