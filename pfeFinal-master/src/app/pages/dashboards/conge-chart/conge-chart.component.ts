import { Component, OnInit } from '@angular/core';
import { ChangeDetectorRef, ViewChild } from '@angular/core';
import { Color, Label } from 'ng2-charts';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts/ng2-charts';
import { TokenStorage } from 'src/app/core/services/tokenservice.service';
import { ChartService } from '../../chart/chart.service';
@Component({
  selector: 'app-conge-chart',
  templateUrl: './conge-chart.component.html',
  styleUrls: ['./conge-chart.component.scss']
})
export class CongeChartComponent implements OnInit {

  @ViewChild("baseChart", { static: false }) chart: BaseChartDirective;
  public barChartLabels: Label[] = [];
  public chartLabels: Label[] = [];

  public barChartType: ChartType = 'bar';
  lstNbr:number[];
  lstNbrr:number[];

  public barChartData: ChartDataSets[] = [
    { data: [], label: 'Series A' },

  ];
  list:any[]=[]
  breadCrumbItems: Array<{}>;



  constructor(private serv :ChartService,private cdRef: ChangeDetectorRef,private token:TokenStorage) { }

  ngOnInit() {
    this.breadCrumbItems = [{ label: 'Charts' }, { label: 'Chartjs chart', active: true }];

  
    this.chart
    this.getChart()

  }

  public barChartOptions: ChartOptions = {
    responsive: true,
    scales: { xAxes: [{ticks: {  beginAtZero: true }}], yAxes: [{}] },
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'end',
        font: {
          size: 20,
        }
      }
    }
  };
  public barChartLegend = true;

  lineChartColors: Color[] = [
    {
      borderColor: '#34c38f',
      backgroundColor: '#556ee6', 
    },
  ];
  
  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public randomize(): void {
    const data = [
      Math.round(Math.random() * 100),
      59,
      80,
      (Math.random() * 100),
      56,
      (Math.random() * 100),
      40];
    const clone = JSON.parse(JSON.stringify(this.barChartData));
    clone[0].data = data;
    this.barChartData = clone;
  
  }

  

 getChart(){
  this.barChartLabels=[]

    this.lstNbr=[]
    this.serv.Getconge(this.token.getUser().matriculeP).subscribe((data:Object[])=>{
      
     this.list=data
     console.log("**************************"+this.list)
      data.forEach(element => {

        this.lstNbr.push(element["nom"]);
        this.barChartLabels.push(element["prenom"]);



      });

      this.lstNbrr = this.lstNbr
      this.cdRef.detectChanges();
      this.chartLabels = this.barChartLabels
       this.barChartData=[
         { data: this.lstNbrr,
            label: 'Nombre de jour de congé' },
       ]
    })
  


  }
}