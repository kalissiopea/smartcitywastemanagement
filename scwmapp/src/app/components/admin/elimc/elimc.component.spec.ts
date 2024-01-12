import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElimcComponent } from './elimc.component';

describe('ElimcComponent', () => {
  let component: ElimcComponent;
  let fixture: ComponentFixture<ElimcComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ElimcComponent]
    });
    fixture = TestBed.createComponent(ElimcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
