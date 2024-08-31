import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IngredientSelectionComponent } from './ingredient-selection.component';

describe('IngredientSelectionComponent', () => {
  let component: IngredientSelectionComponent;
  let fixture: ComponentFixture<IngredientSelectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [IngredientSelectionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IngredientSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
