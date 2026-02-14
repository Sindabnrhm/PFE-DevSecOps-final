import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EspaceRhComponent } from './espace-rh.component';

describe('EspaceRhComponent', () => {
  let component: EspaceRhComponent;
  let fixture: ComponentFixture<EspaceRhComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EspaceRhComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EspaceRhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
