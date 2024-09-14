package main

import "fmt"

type Media interface {
	FindByName(name string) []interface{}
	FindByGenre(name string) []interface{}
	FindByCreator(name string) []interface{}
}

func findByName(media Media, name string) []interface{} {
	result := media.FindByName(name)
	if result != nil {
		return result
	} else {
		fmt.Printf("Couldn't find songs with the name %v", name)
	}
	return nil
}

func findByGenre(media Media, genre string) []interface{} {
	result := media.FindByGenre(genre)
	if result != nil {
		return result
	} else {
		fmt.Printf("Couldn't find songs under the genre %v", genre)
	}
	return nil
}

func findByCreator(media Media, creator string) []interface{} {
	result := media.FindByCreator(creator)
	if result != nil {
		return result
	} else {
		fmt.Printf("Couldn't find songs under the creator %v", creator)
	}
	return nil
}

func main() {
	songs := songData()
	songCollection := SongCollection{songs: songs}

	movies := movieData()
	movieCollection := MovieCollection{movies: movies}

	books := bookData()
	bookCollection := BookCollection{books: books}

	// Example usage
	song := findByName(&songCollection, "Shape of You")
	if song != nil {
		fmt.Println("Found songs:", song)
	}

	movie := findByName(&movieCollection, "Inception")
	if movie != nil {
		fmt.Println("Found songs:", movie)
	}

	book := findByName(&bookCollection, "1984")
	if book != nil {
		fmt.Println("Found songs:", book)
	}
}
