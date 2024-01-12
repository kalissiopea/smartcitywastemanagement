import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IstanziaPercorsoPuliziaComponent } from './istanzia-percorso-pulizia.component';

describe('IstanziaPercorsoPuliziaComponent', () => {
  let component: IstanziaPercorsoPuliziaComponent;
  let fixture: ComponentFixture<IstanziaPercorsoPuliziaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [IstanziaPercorsoPuliziaComponent]
    });
    fixture = TestBed.createComponent(IstanziaPercorsoPuliziaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
