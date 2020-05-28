import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MultipleMediaComponent } from './multiple-media.component';

describe('MultipleMediaComponent', () => {
  let component: MultipleMediaComponent;
  let fixture: ComponentFixture<MultipleMediaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MultipleMediaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MultipleMediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
