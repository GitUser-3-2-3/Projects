package main

import "fmt"

type Movie struct {
	movieName  string
	movieGenre string
	length     float64
	director   string
	actorsList []Actor
}

type Actor struct {
	name string
	age  int
}

type MovieCollection struct {
	movies []Movie
}

func (moviesList *MovieCollection) FindByName(movieName string) []interface{} {
	var result []interface{}

	for _, movie := range moviesList.movies {
		if movie.movieName == movieName {
			result = append(result, movie)
		}
	}
	return result
}

func (moviesList *MovieCollection) FindByGenre(movieGenre string) []interface{} {
	var result []interface{}

	for _, movie := range moviesList.movies {
		if movie.movieGenre == movieGenre {
			result = append(result, movie)
		}
	}
	return result
}

func (moviesList *MovieCollection) FindByCreator(director string) []interface{} {
	var result []interface{}

	for _, movie := range moviesList.movies {
		if movie.director == director {
			result = append(result, movie)
		}
	}
	return result
}

func (moviesList *MovieCollection) FindByActor(actor string) []interface{} {
	var result []interface{}

	for _, movie := range moviesList.movies {
		for _, a := range movie.actorsList {
			if a.name == actor {
				result = append(result, movie)
			}
		}
	}
	return result
}

func (moviesList *MovieCollection) ShowActorsList(movieName, director string) ([]Actor, error) {
	var result []Actor

	for _, movie := range moviesList.movies {
		if movie.movieName == movieName && movie.director == director {
			for _, actor := range movie.actorsList {
				result = append(result, actor)
			}
		} else {
			err := fmt.Errorf("movie: %v and director %v do not match", movieName, director)
			return nil, err
		}
	}
	return result, nil
}
