import { Component, OnInit } from '@angular/core';
import { GamesService } from '../games.service';
import { GameSummary } from '../models';

@Component({
  selector: 'app-list-games',
  templateUrl: './list-games.component.html',
  styleUrls: ['./list-games.component.css'],
})
export class ListGamesComponent implements OnInit {
  limit = 10;
  offset = 0;

  constructor(private gamesService: GamesService) {}

  games$!: Promise<GameSummary[]>;

  ngOnInit(): void {
    this.games$ = this.gamesService.getGames(this.limit, this.offset);
  }

  fetchChanges(limit: string) {
    this.limit = +limit;
    this.games$ = this.gamesService.getGames(this.limit, this.offset);
  }

  page(d: number) {
    if (d >= 0) {
      this.offset += this.limit;
    } else {
      this.offset = Math.max(0, this.offset - this.limit);
    }
    this.games$ = this.gamesService.getGames(this.limit, this.offset);
  }
}
