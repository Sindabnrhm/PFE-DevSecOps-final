import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

  private API_URL = environment.apiUrl;

  constructor(private http: HttpClient) {}

  /**
   * LOGIN
   */
  login(email: string, password: string): Observable<any> {
    return this.http.post(this.API_URL + '/api/auth/signin', {
      username: email,
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
   * CURRENT USER
   */
  currentUser() {
    return JSON.parse(localStorage.getItem('user') || '{}');
  }

  /**
   * RESET PASSWORD
   */
  resetPassword(email: string): Observable<any> {
    return this.http.post(this.API_URL + '/api/auth/reset-password', {
      email: email
    });
  }

  /**
   * LOGOUT
   */
  logout() {
    localStorage.removeItem('user');
  }
}