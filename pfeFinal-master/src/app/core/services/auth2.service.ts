import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class Auth2Service {

  // 👉 URL backend Render
  private API_URL = environment.apiUrl;

  constructor(private http: HttpClient) { }

  // ✅ REGISTER
  register(user: any): Observable<any> {
    return this.http.post(`${this.API_URL}/api/auth/signup`, user);
  }

  // ✅ LOGIN
  login(credentials: any): Observable<any> {
    return this.http.post(`${this.API_URL}/api/auth/signin`, credentials);
  }
}