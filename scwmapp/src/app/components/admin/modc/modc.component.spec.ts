import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModcComponent } from './modc.component';

describe('ModcComponent', () => {
  let component: ModcComponent;
  let fixture: ComponentFixture<ModcComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModcComponent]
    });
    fixture = TestBed.createComponent(ModcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
