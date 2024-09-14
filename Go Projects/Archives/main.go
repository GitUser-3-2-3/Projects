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
	songs := []Song{
		{"Song", "Pop", "Singer1", 3.5},
		{"Song", "Rock", "Singer2", 4.0},
		{"Song3", "Pop", "Singer1", 2.8},
		{"Song4", "Jazz", "Singer3", 5.2},
	}

	songCollection := SongCollection{songs: songs}

	// Example usage
	result := findByName(&songCollection, "Song")
	if result != nil {
		fmt.Println("Found songs:", result)
	}
}