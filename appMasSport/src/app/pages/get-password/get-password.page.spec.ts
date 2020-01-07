import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPasswordPage } from './get-password.page';

describe('GetPasswordPage', () => {
  let component: GetPasswordPage;
  let fixture: ComponentFixture<GetPasswordPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetPasswordPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetPasswordPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
