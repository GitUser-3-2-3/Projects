import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SavedChallengesComponent } from './saved-challenges.component';

describe('SavedChallengesComponent', () => {
  let component: SavedChallengesComponent;
  let fixture: ComponentFixture<SavedChallengesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SavedChallengesComponent]
    });
    fixture = TestBed.createComponent(SavedChallengesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
