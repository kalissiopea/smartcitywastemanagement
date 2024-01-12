import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VediTasseComponent } from './vedi-tasse.component';

describe('VediTasseComponent', () => {
  let component: VediTasseComponent;
  let fixture: ComponentFixture<VediTasseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VediTasseComponent]
    });
    fixture = TestBed.createComponent(VediTasseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
