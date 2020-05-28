import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchedUserProfileComponent } from './searched-user-profile.component';

describe('SearchedUserProfileComponent', () => {
  let component: SearchedUserProfileComponent;
  let fixture: ComponentFixture<SearchedUserProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchedUserProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchedUserProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
