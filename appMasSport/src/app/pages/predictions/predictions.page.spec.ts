import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictionsPage } from './predictions.page';

describe('PredictionsPage', () => {
  let component: PredictionsPage;
  let fixture: ComponentFixture<PredictionsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PredictionsPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PredictionsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
