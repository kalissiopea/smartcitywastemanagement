import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AggcComponent } from './aggc.component';

describe('AggcComponent', () => {
  let component: AggcComponent;
  let fixture: ComponentFixture<AggcComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AggcComponent]
    });
    fixture = TestBed.createComponent(AggcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
