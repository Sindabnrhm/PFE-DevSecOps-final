import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import Swal from 'sweetalert2';
import { RhService } from '../rh.service';
import { TokenStorage } from 'src/app/core/services/tokenservice.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-historique-rh',
  templateUrl: './historique-rh.component.html',
  styleUrls: ['./historique-rh.component.scss']
})
export class HistoriqueRhComponent implements OnInit {

  
  term:string
  p:any

  // bread crumb items
  breadCrumbItems: Array<{}>;

listDemande:any
type:any
demandeForm:FormGroup
  constructor(private rhServ:RhService,private token:TokenStorage,private fb:FormBuilder,private modalService: NgbModal) { }

  ngOnInit() {
    this.breadCrumbItems = [{ label: 'Projects' }, { label: 'Projects Grid', active: true }];

    this.getDemandeChef()
    this.demandeForm=this.fb.group({
      idConge:[""],
      dateCng :[""],
dateDebut : [""],
dateFin : [""],
repRh : [""],
statut : [""]
    })
  }
getDemandeChef(){
  this.rhServ.getDemandeRhNotNull().subscribe((data)=>{
    this.listDemande=data
  
    console.log(this.listDemande)
  })
}
openModal(targetModal, user) {
  this.modalService.open(targetModal, {
   centered: true,
   backdrop: 'static'
  });
 
  this.demandeForm.patchValue({
    idConge: user.idConge,

    dateCng: user.dateCng,
    dateDebut: user.dateDebut,
    dateFin: user.dateFin,
    repRh: user.repRh,
    statut: user.statut,
  

  });
  // this.lib=this.userForm.get('lib_demande').value
  // console.log(this.lib)



 }

}
