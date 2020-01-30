import { Component, OnInit } from '@angular/core';
import { DashboardService } from './dashboard.service';

import { Movie } from '../../model/movie.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  body;
  favouriteGenreMoviesForDisplay: Object[] = [];
  favouriteGenreMoviesComplete: Object[] = []; //to be used later for 'See All' functionality
  constructor(
    private dashboardService: DashboardService,
  ) { }

  ngOnInit(): void {
    this.body = document.getElementsByTagName('body')[0];
    this.body.classList.remove('bg-default');
    this.body.classList.add('grey-background');
    this.getMoviesByPreferredGenres();
  }

  getMoviesByPreferredGenres() {
    this.dashboardService.getMoviesByPreferredGenres().
      subscribe(
        genres => {
          this.favouriteGenreMoviesComplete = [...genres];
          genres.forEach(genre => {
            genre.movies = genre.movies.splice(0, 4);
            while (genre.movies.length < 4) {
              genre.movies.push({
                title: null,
                movieid: null,
                budget: null,
                homepage: null,
                origLang: null,
                origTitle: null,
                overview: null,
                popularity: null,
                releaseDate: null,
                revenue: null,
                runtime: null,
                status: null,
                tagline: null,
                voteAverage: null,
                voteCount: null,
                genre: null,
                keyword: null
              });
            }
            console.log(genre);

          });
          this.favouriteGenreMoviesForDisplay = genres;
        },
        error => {
          console.log('error', error);
        }
      )
  }

}
