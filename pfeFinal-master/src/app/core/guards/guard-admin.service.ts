import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorage } from '../services/tokenservice.service';

@Injectable({
  providedIn: 'root'
})
export class GuardAdminService {

  constructor(private route:Router,     private token: TokenStorage
    ) { }
  canActivate(){
    if(this.token.getToken() && this.token.getUser().role_portail=="ROLE_ADMIN" ){
    return true;
    }else{
    this.route.navigate(['/account/login']);
    return false;
    }
    }
    }