import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgguComponent } from './aggu.component';

describe('AgguComponent', () => {
  let component: AgguComponent;
  let fixture: ComponentFixture<AgguComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AgguComponent]
    });
    fixture = TestBed.createComponent(AgguComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
