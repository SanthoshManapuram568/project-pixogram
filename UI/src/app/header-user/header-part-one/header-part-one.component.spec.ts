import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderPartOneComponent } from './header-part-one.component';

describe('HeaderPartOneComponent', () => {
  let component: HeaderPartOneComponent;
  let fixture: ComponentFixture<HeaderPartOneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeaderPartOneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderPartOneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
