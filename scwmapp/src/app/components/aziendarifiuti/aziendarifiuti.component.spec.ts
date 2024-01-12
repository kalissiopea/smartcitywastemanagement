import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AziendarifiutiComponent } from './aziendarifiuti.component';

describe('AziendarifiutiComponent', () => {
  let component: AziendarifiutiComponent;
  let fixture: ComponentFixture<AziendarifiutiComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AziendarifiutiComponent]
    });
    fixture = TestBed.createComponent(AziendarifiutiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
