package main

func songData() []Song {
	songs := []Song{
		{
			songName:  "Shape of You",
			songGenre: "Pop",
			singer:    "Ed Sheeran",
			length:    3.53,
		},
		{
			songName:  "Blinding Lights",
			songGenre: "Synthwave",
			singer:    "The Weeknd",
			length:    3.20,
		},
		{
			songName:  "Rolling in the Deep",
			songGenre: "Soul",
			singer:    "Adele",
			length:    3.48,
		},
		{
			songName:  "Uptown Funk",
			songGenre: "Funk",
			singer:    "Mark Ronson ft. Bruno Mars",
			length:    4.30,
		},
		{
			songName:  "Someone Like You",
			songGenre: "Pop",
			singer:    "Adele",
			length:    4.45,
		},
		{
			songName:  "Can't Stop the Feeling!",
			songGenre: "Pop",
			singer:    "Justin Timberlake",
			length:    3.56,
		},
		{
			songName:  "Bad Guy",
			songGenre: "Electropop",
			singer:    "Billie Eilish",
			length:    3.14,
		},
		{
			songName:  "Old Town Road",
			songGenre: "Country Rap",
			singer:    "Lil Nas X",
			length:    2.37,
		},
		{
			songName:  "Senorita",
			songGenre: "Latin Pop",
			singer:    "Shawn Mendes & Camila Cabello",
			length:    3.11,
		},
		{
			songName:  "Havana",
			songGenre: "Pop",
			singer:    "Camila Cabello",
			length:    3.36,
		},
		{
			songName:  "Perfect",
			songGenre: "Pop",
			singer:    "Ed Sheeran",
			length:    4.23,
		},
		{
			songName:  "Sunflower",
			songGenre: "Hip Hop",
			singer:    "Post Malone & Swae Lee",
			length:    2.38,
		},
		{
			songName:  "Shallow",
			songGenre: "Pop",
			singer:    "Lady Gaga & Bradley Cooper",
			length:    3.36,
		},
		{
			songName:  "Dance Monkey",
			songGenre: "Electropop",
			singer:    "Tones and I",
			length:    3.29,
		},
		{
			songName:  "Rockstar",
			songGenre: "Hip Hop",
			singer:    "Post Malone ft. 21 Savage",
			length:    3.38,
		},
		{
			songName:  "Closer",
			songGenre: "Electropop",
			singer:    "The Chainsmokers ft. Halsey",
			length:    4.04,
		},
		{
			songName:  "God's Plan",
			songGenre: "Hip Hop",
			singer:    "Drake",
			length:    3.18,
		},
		{
			songName:  "Thank U, Next",
			songGenre: "Pop",
			singer:    "Ariana Grande",
			length:    3.27,
		},
		{
			songName:  "Lucid Dreams",
			songGenre: "Hip Hop",
			singer:    "Juice WRLD",
			length:    3.59,
		},
		{
			songName:  "Sicko Mode",
			songGenre: "Hip Hop",
			singer:    "Travis Scott",
			length:    5.12,
		},
	}
	return songs
}

func movieData() []Movie {
	movies := []Movie{
		{
			movieName:  "Inception",
			movieGenre: "Sci-Fi",
			director:   "Christopher Nolan",
			length:     148,
		},
		{
			movieName:  "The Dark Knight",
			movieGenre: "Action",
			director:   "Christopher Nolan",
			length:     152,
		},
		{
			movieName:  "Interstellar",
			movieGenre: "Sci-Fi",
			director:   "Christopher Nolan",
			length:     169,
		},
		{
			movieName:  "The Matrix",
			movieGenre: "Sci-Fi",
			director:   "Lana Wachowski, Lilly Wachowski",
			length:     136,
		},
		{
			movieName:  "Pulp Fiction",
			movieGenre: "Crime",
			director:   "Quentin Tarantino",
			length:     154,
		},
		{
			movieName:  "The Shawshank Redemption",
			movieGenre: "Drama",
			director:   "Frank Darabont",
			length:     142,
		},
		{
			movieName:  "The Godfather",
			movieGenre: "Crime",
			director:   "Francis Ford Coppola",
			length:     175,
		},
		{
			movieName:  "The Godfather: Part II",
			movieGenre: "Crime",
			director:   "Francis Ford Coppola",
			length:     202,
		},
		{
			movieName:  "Fight Club",
			movieGenre: "Drama",
			director:   "David Fincher",
			length:     139,
		},
		{
			movieName:  "Forrest Gump",
			movieGenre: "Drama",
			director:   "Robert Zemeckis",
			length:     142,
		},
		{
			movieName:  "The Lord of the Rings: The Fellowship of the Ring",
			movieGenre: "Fantasy",
			director:   "Peter Jackson",
			length:     178,
		},
		{
			movieName:  "The Lord of the Rings: The Two Towers",
			movieGenre: "Fantasy",
			director:   "Peter Jackson",
			length:     179,
		},
		{
			movieName:  "The Lord of the Rings: The Return of the King",
			movieGenre: "Fantasy",
			director:   "Peter Jackson",
			length:     201,
		},
		{
			movieName:  "Star Wars: Episode IV - A New Hope",
			movieGenre: "Sci-Fi",
			director:   "George Lucas",
			length:     121,
		},
		{
			movieName:  "Star Wars: Episode V - The Empire Strikes Back",
			movieGenre: "Sci-Fi",
			director:   "Irvin Kershner",
			length:     124,
		},
		{
			movieName:  "Star Wars: Episode VI - Return of the Jedi",
			movieGenre: "Sci-Fi",
			director:   "Richard Marquand",
			length:     131,
		},
		{
			movieName:  "The Avengers",
			movieGenre: "Action",
			director:   "Joss Whedon",
			length:     143,
		},
		{
			movieName:  "Avengers: Infinity War",
			movieGenre: "Action",
			director:   "Anthony Russo, Joe Russo",
			length:     149,
		},
		{
			movieName:  "Avengers: Endgame",
			movieGenre: "Action",
			director:   "Anthony Russo, Joe Russo",
			length:     181,
		},
		{
			movieName:  "Titanic",
			movieGenre: "Romance",
			director:   "James Cameron",
			length:     195,
		},
	}
	return movies
}

func bookData() []Book {
	books := []Book{
		{
			bookName:  "To Kill a Mockingbird",
			bookGenre: "Fiction",
			author:    "Harper Lee",
			pages:     281,
		},
		{
			bookName:  "1984",
			bookGenre: "Dystopian",
			author:    "George Orwell",
			pages:     328,
		},
		{
			bookName:  "Moby Dick",
			bookGenre: "Adventure",
			author:    "Herman Melville",
			pages:     635,
		},
		{
			bookName:  "The Great Gatsby",
			bookGenre: "Fiction",
			author:    "F. Scott Fitzgerald",
			pages:     180,
		},
		{
			bookName:  "Pride and Prejudice",
			bookGenre: "Romance",
			author:    "Jane Austen",
			pages:     279,
		},
		{
			bookName:  "War and Peace",
			bookGenre: "Historical",
			author:    "Leo Tolstoy",
			pages:     1225,
		},
		{
			bookName:  "The Catcher in the Rye",
			bookGenre: "Fiction",
			author:    "J.D. Salinger",
			pages:     214,
		},
		{
			bookName:  "The Hobbit",
			bookGenre: "Fantasy",
			author:    "J.R.R. Tolkien",
			pages:     310,
		},
		{
			bookName:  "Fahrenheit 451",
			bookGenre: "Dystopian",
			author:    "Ray Bradbury",
			pages:     194,
		},
		{
			bookName:  "Jane Eyre",
			bookGenre: "Romance",
			author:    "Charlotte Bronte",
			pages:     500,
		},
		{
			bookName:  "Brave New World",
			bookGenre: "Dystopian",
			author:    "Aldous Huxley",
			pages:     268,
		},
		{
			bookName:  "The Lord of the Rings: The Fellowship of the Ring",
			bookGenre: "Fantasy",
			author:    "J.R.R. Tolkien",
			pages:     423,
		},
		{
			bookName:  "The Lord of the Rings: The Two Towers",
			bookGenre: "Fantasy",
			author:    "J.R.R. Tolkien",
			pages:     352,
		},
		{
			bookName:  "The Lord of the Rings: The Return of the King",
			bookGenre: "Fantasy",
			author:    "J.R.R. Tolkien",
			pages:     416,
		},
		{
			bookName:  "The Alchemist",
			bookGenre: "Adventure",
			author:    "Paulo Coelho",
			pages:     208,
		},
		{
			bookName:  "The Da Vinci Code",
			bookGenre: "Mystery",
			author:    "Dan Brown",
			pages:     454,
		},
		{
			bookName:  "Harry Potter and the Sorcerer's Stone",
			bookGenre: "Fantasy",
			author:    "J.K. Rowling",
			pages:     309,
		},
		{
			bookName:  "Harry Potter and the Chamber of Secrets",
			bookGenre: "Fantasy",
			author:    "J.K. Rowling",
			pages:     341,
		},
		{
			bookName:  "Harry Potter and the Prisoner of Azkaban",
			bookGenre: "Fantasy",
			author:    "J.K. Rowling",
			pages:     435,
		},
		{
			bookName:  "Harry Potter and the Goblet of Fire",
			bookGenre: "Fantasy",
			author:    "J.K. Rowling",
			pages:     734,
		},
	}
	return books
}
