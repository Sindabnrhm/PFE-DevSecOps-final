import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TokenStorage } from 'src/app/core/services/tokenservice.service';
import Swal from 'sweetalert2';
import { CongeService } from '../conge.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-basic',
  templateUrl: './basic.component.html',
  styleUrls: ['./basic.component.scss']
})

/**
 * Basic table component
 */
export class BasicComponent implements OnInit {
  // bread crumb items
  lib:any

  breadCrumbItems: Array<{}>;
typeCng:any
Cng:any
solde:any
dateCng=new Date()
sancForm:FormGroup
  constructor(private modalService: NgbModal,private formBuilder : FormBuilder
    ,private congeService:CongeService,private token:TokenStorage,private datePipe: DatePipe) { }

  formConge:FormGroup

    ngOnInit(): void {
      this.breadCrumbItems = [{ label: 'Congé' }, { label: 'Gestion des congés', active: true }];
this.getTypeConge()
this.getCongeByMat()
console.log(this.datePipe.transform(this.dateCng,'yyyy-MM-dd HH:mm:ss'))
      this.formConge = this.formBuilder.group({
        idConge: [''],

        dateCng : [this.datePipe.transform(this.dateCng,'yyyy-MM-dd HH:mm:ss')],      
        matriculeP:[this.token.getUser().matriculeP],
        dateDebut:['',Validators.required],
        dateFin:['',Validators.required],
        nom:[this.token.getUser().departement],
        prenom:[this.token.getUser().nomResponsable],
        duree:['',Validators.required],
        statut:['',Validators.required],
        typeConge:this.formBuilder.group({
          idType:['',Validators.required]
        }),
     
      });

  this.sancForm=this.formBuilder.group({
    dateSanction:[this.datePipe.transform(this.dateCng, 'yyyy-MM-dd')],
    matriculeP:[this.token.getUser().matriculeP],
    personnel:this.formBuilder.group({
      idEmploye:['1']
    }),
  })
    }
    openModalAjout(content: any) {
      this.modalService.open(content, { size: 'lg', centered: true });
  
    }
    
  
  openModalUpdate(content,conge) {
    this.modalService.open(content, { size: 'lg', centered: true });

    this.formConge.patchValue({
      idConge: conge.idConge,
      dateCng:conge.dateCng,
      matriculeP: conge.matriculeP,
      dateDebut: conge.dateDebut,
      dateFin: conge.dateFin,
      duree: conge.duree,
      statut: conge.statut,
      typeConge: {
        idType: conge.typeConge.idType
      }
    });
    this.lib=this.formConge.get('matriculeP').value

    console.log(this.lib)
    console.log(this.formConge.value)
  }


  getTypeConge() {
  
    this.congeService.GetTypeConge().subscribe((data: any) => {
      this.typeCng = data;
      console.log( this.typeCng)
    });
  }

  getCongeByMat() {
    console.log(this.token.getUser().matriculeP)
    this.congeService.GetCongeByMat(this.token.getUser().matriculeP).subscribe((data: any) => {
      this.Cng = data;
    });
  }

  ajoutConge(){
    console.log(this.formConge.value)
    this.congeService.getMaxSolde(this.token.getUser().matriculeP).subscribe((data)=>{
this.solde=data
console.log(this.solde)
    })


    this.congeService.addConge(this.formConge.value).subscribe((data:any)=>{
if (data){


}
else{
  Swal.fire({
    position: 'top-end',
    icon: 'success',
    title: 'Conge ajouté avec succés ',
    showConfirmButton: false,
    timer: 3000
  });
  if(this.solde<0){
    console.log(this.sancForm.value)
    this.congeService.addSanction(this.sancForm.value).subscribe(data=>{
      
    })
  }
}
    })
    this.getCongeByMat()
    this.modalService.dismissAll();

  }
  confirm(id) {
    this.congeService.DeleteCng(id).subscribe((data)=>{
          
    })
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
        Swal.fire('Deleted!', 'Your file has been deleted.', 'success');
      }
    });
    this.getCongeByMat()
  }
  deleteConge(id:any){
    console.log(id)
    this.congeService.DeleteCng(id).subscribe((data)=>{
      this.getCongeByMat()
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
        this.congeService.DeleteCng(id).subscribe((data)=>{
          
        })
      }
    })
  }
}
