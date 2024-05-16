import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ChallengeComponent} from "./challenge/challenge.component";
import {SavedChallengesComponent} from "./saved-challenges/saved-challenges.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: ChallengeComponent},
  {path: 'saved-challenges', component: SavedChallengesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
