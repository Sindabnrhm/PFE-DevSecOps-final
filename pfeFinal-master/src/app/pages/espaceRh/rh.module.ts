import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProjectsRoutingModule } from './rh-routing.module';
import { UIModule } from '../../shared/ui/ui.module';

import { DropzoneModule } from 'ngx-dropzone-wrapper';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbDropdownModule, NgbTooltipModule, NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { NgApexchartsModule } from 'ng-apexcharts';
import { NgxPaginationModule } from 'ngx-pagination';


import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { EspaceRhComponent } from './espace-rh/espace-rh.component';
import { HistoriqueRhComponent } from './historique-rh/historique-rh.component';
import { GestionutilisateurComponent } from './gestionutilisateur/gestionutilisateur.component';
import { GestionServiceComponent } from './gestion-service/gestion-service.component';
import { GestionTypeCongeComponent } from './gestion-type-conge/gestion-type-conge.component';

@NgModule({
  declarations: [
    EspaceRhComponent,
    HistoriqueRhComponent,
    GestionutilisateurComponent,
    GestionServiceComponent,
    GestionTypeCongeComponent
  ],
  imports: [
    CommonModule,
    ProjectsRoutingModule,
    UIModule,
    NgbDropdownModule,
    NgbTooltipModule,
    NgApexchartsModule,
    DropzoneModule,
    FormsModule,
    NgbDatepickerModule,
    Ng2SearchPipeModule,
    NgxPaginationModule,
    ReactiveFormsModule
  ]
})

export class RhModule { }
