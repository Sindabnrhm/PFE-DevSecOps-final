import { TestBed } from '@angular/core/testing';

import { ChefServiceService } from './chef-service.service';

describe('ChefServiceService', () => {
  let service: ChefServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChefServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
