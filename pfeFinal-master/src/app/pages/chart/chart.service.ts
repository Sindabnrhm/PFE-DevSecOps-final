import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChartService {

  private header: HttpHeaders;
  constructor(private httpClient: HttpClient) {
    this.header = new HttpHeaders();
  }

  Getconge = (mat:any): Observable<any[]> => {
    return this.httpClient.get<any[]>("http://localhost:8080/conge/getCng/"+mat);
  };
  GetNbrconge = (mat:any): Observable<any[]> => {
    return this.httpClient.get<any[]>("http://localhost:8080/conge/getNbrCng/"+mat);
  };


}
