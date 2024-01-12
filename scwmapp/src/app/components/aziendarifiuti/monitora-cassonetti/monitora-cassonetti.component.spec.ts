import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonitoraCassonettiComponent } from './monitora-cassonetti.component';

describe('MonitoraCassonettiComponent', () => {
  let component: MonitoraCassonettiComponent;
  let fixture: ComponentFixture<MonitoraCassonettiComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MonitoraCassonettiComponent]
    });
    fixture = TestBed.createComponent(MonitoraCassonettiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
