import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GiudizioComponent } from './giudizio.component';

describe('GiudizioComponent', () => {
  let component: GiudizioComponent;
  let fixture: ComponentFixture<GiudizioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GiudizioComponent]
    });
    fixture = TestBed.createComponent(GiudizioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
