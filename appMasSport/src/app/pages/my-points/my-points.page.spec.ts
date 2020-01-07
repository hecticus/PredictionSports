import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyPointsPage } from './my-points.page';

describe('MyPointsPage', () => {
  let component: MyPointsPage;
  let fixture: ComponentFixture<MyPointsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyPointsPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyPointsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
