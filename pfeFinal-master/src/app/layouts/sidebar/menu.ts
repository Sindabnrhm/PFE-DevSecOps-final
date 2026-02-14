import { MenuItem } from './menu.model';

export const MENU: MenuItem[] = [
    {
        id: 1,
        label: 'MENUITEMS.MENU.TEXT',
        isTitle: true
    },

    {
        id: 2,
        label: 'MENUITEMS.DASHBOARDS.TEXT',
        icon: 'bx-home-circle',
        badge: {
            variant: 'info',
            text: 'MENUITEMS.DASHBOARDS.BADGE',
        },
        
        subItems: [
            {
                id: 3,
                label: 'MENUITEMS.DASHBOARDS.LIST.DEFAULT',
                link: '/dashboard',
                parentId: 2
            },
           
            {
                id: 5,
                label: 'MENUITEMS.DASHBOARDS.LIST.CRYPTO',
                link: '/dashboards/crypto',
                parentId: 2
            },
            
        ]
    },

    {
        id: 63,
        label: 'MENUITEMS.CALENDAR.TEXT',
        icon: 'bx bx-calendar-event',

        link: '/calendar',
    },
    {
        id: 8,
        label: 'Interface',
        isTitle: true
    },






    {
        id: 39,
        label: 'Espace chef',
        icon: 'bx-briefcase-alt-2',
        isLayout: true,
        subItems: [
            {
                id: 40,
                label: 'Demande Chef',
                link: '/projects/grid',
                parentId: 38
            },
            {
                id: 41,
                label: 'Historique Demande chef',
                link: '/projects/list',
                parentId: 38
            },

        ]
    },


    {
        id: 39,
        label: 'Espace rh',
        icon: 'bx-briefcase-alt-2',
        isRh:true,
        subItems: [
            {
                id: 40,
                label: 'Demande Rh',
                link: '/RH/demandeRh',
                parentId: 38
            },
            {
                id: 41,
                label: 'Historique Rh',
                link: '/RH/historiqueRh',
                parentId: 38
            },
            {
                id: 42,
                label: 'Gestion utilisateur',
                link: '/RH/gestionUtilisateur',
                parentId: 39
            },
            {
                id: 43,
                label: 'Gestion Service',
                link: '/RH/Service',
                parentId: 40
            },
            {
                id: 44,
                label: 'Gestion Type Conge',
                link: '/RH/TypeConge',
                parentId: 41
            }
        ]
    },  



    {
        id: 110,
        icon: 'bx-list-ul',
        label: 'Demande congés',
        subItems: [
            {
                id: 111,
                label: 'Demande',
                link: '/tables/basic',
                parentId: 110
            },

        ]
    },



];

