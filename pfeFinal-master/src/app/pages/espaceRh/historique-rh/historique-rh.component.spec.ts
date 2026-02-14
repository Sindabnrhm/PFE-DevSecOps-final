import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoriqueRhComponent } from './historique-rh.component';

describe('HistoriqueRhComponent', () => {
  let component: HistoriqueRhComponent;
  let fixture: ComponentFixture<HistoriqueRhComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoriqueRhComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoriqueRhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
