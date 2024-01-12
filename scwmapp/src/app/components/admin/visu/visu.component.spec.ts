import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisuComponent } from './visu.component';

describe('VisuComponent', () => {
  let component: VisuComponent;
  let fixture: ComponentFixture<VisuComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VisuComponent]
    });
    fixture = TestBed.createComponent(VisuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
