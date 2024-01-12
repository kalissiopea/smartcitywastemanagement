import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmettiTassaComponent } from './emetti-tassa.component';

describe('EmettiTassaComponent', () => {
  let component: EmettiTassaComponent;
  let fixture: ComponentFixture<EmettiTassaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EmettiTassaComponent]
    });
    fixture = TestBed.createComponent(EmettiTassaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
