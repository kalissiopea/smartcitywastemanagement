import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaTasseComponent } from './visualizza-tasse.component';

describe('VisualizzaTasseComponent', () => {
  let component: VisualizzaTasseComponent;
  let fixture: ComponentFixture<VisualizzaTasseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VisualizzaTasseComponent]
    });
    fixture = TestBed.createComponent(VisualizzaTasseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
