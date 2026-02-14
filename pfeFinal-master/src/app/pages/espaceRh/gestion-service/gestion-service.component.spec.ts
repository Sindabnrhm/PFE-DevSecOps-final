import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionServiceComponent } from './gestion-service.component';

describe('GestionServiceComponent', () => {
  let component: GestionServiceComponent;
  let fixture: ComponentFixture<GestionServiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionServiceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
