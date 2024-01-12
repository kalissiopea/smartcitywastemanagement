import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PagareComponent } from './pagare.component';

describe('PagareComponent', () => {
  let component: PagareComponent;
  let fixture: ComponentFixture<PagareComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PagareComponent]
    });
    fixture = TestBed.createComponent(PagareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
