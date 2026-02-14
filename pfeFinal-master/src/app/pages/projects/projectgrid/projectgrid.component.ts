import { Component, OnInit } from '@angular/core';

import { Project } from '../project.model';

import { projectData } from '../projectdata';
import { ChefServiceService } from '../chef-service.service';
import { TokenStorage } from 'src/app/core/services/tokenservice.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-projectgrid',
  templateUrl: './projectgrid.component.html',
  styleUrls: ['./projectgrid.component.scss']
})

/**
 * Projects-grid component
 */
export class ProjectgridComponent implements OnInit {
  term:string
  p:any

  // bread crumb items
  breadCrumbItems: Array<{}>;

  projectData: Project[];
listDemande:any
type:any
demandeForm:FormGroup
  constructor(private chefServ:ChefServiceService,private token:TokenStorage,private fb:FormBuilder,private modalService: NgbModal) { }

  ngOnInit() {
    this.breadCrumbItems = [{ label: 'Projects' }, { label: 'Projects Grid', active: true }];

    this.projectData = projectData;
    this.getDemandeChef()
    this.demandeForm=this.fb.group({
      idConge:[""],
      dateCng :[""],
dateDebut : [""],
dateFin : [""],
repChef : [""],
statut : [""]
    })
  }
getDemandeChef(){
  this.chefServ.getDemandeChef(this.token.getUser().serv.idService).subscribe((data)=>{
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
    repChef: user.repChef,
    statut: user.statut,
  

  });
  // this.lib=this.userForm.get('lib_demande').value
  // console.log(this.lib)



 }
 updateDemande(){
  console.log(this.demandeForm.value)

  this.chefServ.UpdateDemande(this.demandeForm.value)
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
