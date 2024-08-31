import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PizzaDesignComponent } from './pizza-design/pizza-design.component';
import { IngredientSelectionComponent } from './ingredient-selection/ingredient-selection.component';

@NgModule({
  declarations: [
    AppComponent,
    PizzaDesignComponent,
    IngredientSelectionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
