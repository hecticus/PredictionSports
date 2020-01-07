import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigurationsPage } from './configurations.page';

describe('ConfigurationsPage', () => {
  let component: ConfigurationsPage;
  let fixture: ComponentFixture<ConfigurationsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfigurationsPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfigurationsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
