import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaPagamentiComponent } from './visualizza-pagamenti.component';

describe('VisualizzaPagamentiComponent', () => {
  let component: VisualizzaPagamentiComponent;
  let fixture: ComponentFixture<VisualizzaPagamentiComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VisualizzaPagamentiComponent]
    });
    fixture = TestBed.createComponent(VisualizzaPagamentiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
