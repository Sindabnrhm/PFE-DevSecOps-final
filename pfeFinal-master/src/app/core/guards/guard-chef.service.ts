import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorage } from '../services/tokenservice.service';

@Injectable({
  providedIn: 'root'
})
export class GuardChefService {

  constructor(private route:Router,     private token: TokenStorage
    ) { }
  canActivate(){
    if(this.token.getToken() && this.token.getUser().role_portail=="ROLE_CHEF" ){
    return true;
    }else{
    this.route.navigate(['/account/login']);
    return false;
    }
    }
    }