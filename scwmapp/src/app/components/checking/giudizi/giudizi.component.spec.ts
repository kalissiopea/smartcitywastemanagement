import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GiudiziComponent } from './giudizi.component';

describe('GiudiziComponent', () => {
  let component: GiudiziComponent;
  let fixture: ComponentFixture<GiudiziComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GiudiziComponent]
    });
    fixture = TestBed.createComponent(GiudiziComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
