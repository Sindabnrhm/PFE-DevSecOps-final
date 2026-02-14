import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { RhService } from '../rh.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TokenStorage } from 'src/app/core/services/tokenservice.service';
@Component({
  selector: 'app-gestion-service',
  templateUrl: './gestion-service.component.html',
  styleUrls: ['./gestion-service.component.scss']
})
export class GestionServiceComponent implements OnInit {

 // bread crumb items
 breadCrumbItems: Array<{}>;
 typeCng:any
 Cng:any
 solde:any
 dateCng=new Date()
 formService:FormGroup
   constructor(private modalService: NgbModal,private formBuilder : FormBuilder
     ,private congeService:RhService,private token:TokenStorage) { }
 
 
     ngOnInit(): void {
       this.breadCrumbItems = [{ label: 'Congé' }, { label: 'Gestion des congés', active: true }];
 this.getService()
       this.formService = this.formBuilder.group({
 
        idService:[''],
        cod_serv:['',Validators.required],
        lib_serv:['',Validators.required],
        matriculeChef:['',Validators.required],
        
       });
 
     }
     openModalAjout(content: any) {
       this.modalService.open(content, { size: 'lg', centered: true });
       this.formService.reset()

     }
     
   
   openModalUpdate(content: any,conge:any) {
     this.modalService.open(content, { size: 'lg', centered: true });
 
     this.formService.patchValue({
      idService: conge.idService,
      cod_serv:conge.cod_serv,
      lib_serv: conge.lib_serv,
      matriculeChef: conge.matriculeChef,
       
     });
     console.log(this.formService.value)

   }
 
 
   getService() {
     console.log(this.token.getUser().matriculeP)
     this.congeService.getService().subscribe((data: any) => {
       this.typeCng = data;
     });
   }
 

 
   ajoutservice(){

 console.log(this.formService.value)
 
     this.congeService.addService(this.formService.value).subscribe((data:any)=>{
 if (data){
 
  Swal.fire({
    position: 'top-end',
    icon: 'success',
    title: 'Conge ajouté avec succés ',
    showConfirmButton: false,
    timer: 3000
  });
  this.modalService.dismissAll()
  this.formService.reset()
  this.getService()
 }
 else{
  

 }
     })
 
   }
   confirm(id) {

     Swal.fire({
       title: 'Are you sure?',
       text: 'You won\'t be able to revert this!',
       icon: 'warning',
       showCancelButton: true,
       confirmButtonColor: '#34c38f',
       cancelButtonColor: '#f46a6a',
       confirmButtonText: 'Yes, delete it!'
     }).then(result => {
       if (result.value) {
        this.congeService.DeleteService(id).subscribe((data)=>{
          this.getService()
        
         })
         Swal.fire('Deleted!', 'Your file has been deleted.', 'success');
       }
     });
     this.getService()
   }
   deleteConge(id:any){
     console.log(id)
     this.congeService.DeleteService(id).subscribe((data)=>{
       this.getService()
       console.log("ok"+data)
       if(data==null){
         Swal.fire({
           position: 'top-end',
           icon: 'error',
           title: 'Vous ne pouvez pas supprimer votre demande ',
           showConfirmButton: false,
           timer: 3000
         }); 
       }
       else{
         this.congeService.DeleteService(id).subscribe((data)=>{
          this.getService()

         })
       }
     })
   }
 }
 