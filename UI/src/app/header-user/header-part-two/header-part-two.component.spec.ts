import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderPartTwoComponent } from './header-part-two.component';

describe('HeaderPartTwoComponent', () => {
  let component: HeaderPartTwoComponent;
  let fixture: ComponentFixture<HeaderPartTwoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeaderPartTwoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderPartTwoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
