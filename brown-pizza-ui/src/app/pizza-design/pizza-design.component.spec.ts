import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PizzaDesignComponent } from './pizza-design.component';

describe('PizzaDesignComponent', () => {
  let component: PizzaDesignComponent;
  let fixture: ComponentFixture<PizzaDesignComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PizzaDesignComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PizzaDesignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
