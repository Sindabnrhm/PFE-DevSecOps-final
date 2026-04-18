import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

  private API_URL = environment.apiUrl;

  constructor(private http: HttpClient) {}

  /**
   * LOGIN → backend Spring Boot
   */
  login(email: string, password: string): Observable<any> {
    return this.http.post(this.API_URL + '/api/auth/signin', {
      username: email,   // ⚠️ adapte si backend attend autre chose
      password: password
    });
  }

  /**
   * REGISTER
   */
  register(email: string, password: string): Observable<any> {
    return this.http.post(this.API_URL + '/api/auth/signup', {
      username: email,
      password: password
    });
  }

  /**
   * LOGOUT
   */
  logout() {
    localStorage.removeItem('user');
  }
}