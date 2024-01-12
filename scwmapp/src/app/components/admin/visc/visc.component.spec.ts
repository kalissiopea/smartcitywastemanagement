import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViscComponent } from './visc.component';

describe('ViscComponent', () => {
  let component: ViscComponent;
  let fixture: ComponentFixture<ViscComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViscComponent]
    });
    fixture = TestBed.createComponent(ViscComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
