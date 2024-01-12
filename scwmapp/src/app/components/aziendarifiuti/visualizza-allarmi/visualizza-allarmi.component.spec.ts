import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaAllarmiComponent } from './visualizza-allarmi.component';

describe('VisualizzaAllarmiComponent', () => {
  let component: VisualizzaAllarmiComponent;
  let fixture: ComponentFixture<VisualizzaAllarmiComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VisualizzaAllarmiComponent]
    });
    fixture = TestBed.createComponent(VisualizzaAllarmiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
