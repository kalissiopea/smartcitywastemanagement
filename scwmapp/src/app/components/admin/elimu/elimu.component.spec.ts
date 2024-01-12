import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElimuComponent } from './elimu.component';

describe('ElimuComponent', () => {
  let component: ElimuComponent;
  let fixture: ComponentFixture<ElimuComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ElimuComponent]
    });
    fixture = TestBed.createComponent(ElimuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
