import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CongeChartComponent } from './conge-chart.component';

describe('CongeChartComponent', () => {
  let component: CongeChartComponent;
  let fixture: ComponentFixture<CongeChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CongeChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CongeChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
