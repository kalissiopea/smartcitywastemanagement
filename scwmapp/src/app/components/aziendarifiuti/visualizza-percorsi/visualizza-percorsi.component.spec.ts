import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaPercorsiComponent } from './visualizza-percorsi.component';

describe('VisualizzaPercorsiComponent', () => {
  let component: VisualizzaPercorsiComponent;
  let fixture: ComponentFixture<VisualizzaPercorsiComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VisualizzaPercorsiComponent]
    });
    fixture = TestBed.createComponent(VisualizzaPercorsiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
