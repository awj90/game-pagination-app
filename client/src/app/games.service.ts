import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, firstValueFrom } from 'rxjs';
import { GameSummary } from './models';

const API_URL = 'http://localhost:8080'; // unlike spring-boot, Angular unable to set as env variable

@Injectable()
export class GamesService {
  constructor(private http: HttpClient) {}

  // Get as observable
  //   getGames(limit = 10, skip = 0): Observable<GameSummary[]> {
  //     const params = new HttpParams().set('limit', limit).set('skip', skip);
  //     return this.http.get<GameSummary[]>(`${API_URL}/api/games`, { params });
  //   }

  // Get as promise
  getGames(limit = 10, offset = 0): Promise<GameSummary[]> {
    const params = new HttpParams().set('limit', limit).set('offset', offset);
    return firstValueFrom(
      this.http.get<GameSummary[]>(`${API_URL}/api/games`, { params })
    );
  }
}
