import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EspaceRhComponent } from './espace-rh/espace-rh.component';
import { HistoriqueRhComponent } from './historique-rh/historique-rh.component';
import { GestionutilisateurComponent } from './gestionutilisateur/gestionutilisateur.component';
import { GestionServiceComponent } from './gestion-service/gestion-service.component';
import { GestionTypeCongeComponent } from './gestion-type-conge/gestion-type-conge.component';



const routes: Routes = [
    {
        path: 'demandeRh',
        component: EspaceRhComponent
    },
    {
        path: 'historiqueRh',
        component: HistoriqueRhComponent
    },
    {
        path: 'gestionUtilisateur',
        component: GestionutilisateurComponent
    },
    // {
    //     path: 'create',
    //     component: CreateComponent
    // }
    {
        path: 'Service',
        component: GestionServiceComponent
    },
    {
        path: 'TypeConge',
        component: GestionTypeCongeComponent
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ProjectsRoutingModule {}
