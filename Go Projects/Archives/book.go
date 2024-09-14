package main

type Book struct {
	bookName  string
	bookGenre string
	pages     float64
	author    string
}

type BookCollection struct {
	books []Book
}

func (booksList *BookCollection) FindByName(bookName string) []interface{} {
	var result []interface{}
	for _, book := range booksList.books {
		if book.bookName == bookName {
			result = append(result, book)
		}
	}
	return result
}

func (booksList *BookCollection) FindByGenre(genre string) []interface{} {
	var result []interface{}
	for _, book := range booksList.books {
		if book.bookGenre == genre {
			result = append(result, book)
		}
	}
	return result
}

func (booksList *BookCollection) FindByCreator(author string) []interface{} {
	var result []interface{}
	for _, book := range booksList.books {
		if book.author == author {
			result = append(result, book)
		}
	}
	return result
}
