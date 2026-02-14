import { Component, OnInit } from '@angular/core';
import { RhService } from '../rh.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { TokenStorage } from 'src/app/core/services/tokenservice.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-espace-rh',
  templateUrl: './espace-rh.component.html',
  styleUrls: ['./espace-rh.component.scss']
})
export class EspaceRhComponent implements OnInit {

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
  this.rhServ.getDemandeRh().subscribe((data)=>{
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
 updateDemande(){
  console.log(this.demandeForm.value)

  this.rhServ.UpdateDemandeRh(this.demandeForm.value)
   .subscribe({
     next:(res)=>{
       if (res) {
         if (res) {
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Votre demande à été bien enregistrer !',
            showConfirmButton: false,
            timer: 2000
          });
          // this.toastr.success(' agence updated!', 'update effectuée avec succés.');
       //  this.userForm.reset();
       this.getDemandeChef();
         this.modalService.dismissAll();
         } else {
          // this.toastr.error('Echec update', 'Problème de suppression.');
         }      

               } 

     },

    

   })

}
}
