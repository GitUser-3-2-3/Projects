package main

type Song struct {
	songName  string
	songGenre string
	singer    string
	length    float64
}

type SongCollection struct {
	songs []Song
}

func (songsList *SongCollection) FindByName(name string) []interface{} {
	var result []interface{}
	for _, song := range songsList.songs {
		if song.songName == name {
			result = append(result, song)
		}
	}
	return result
}

func (songsList *SongCollection) FindByGenre(genre string) []interface{} {
	var result []interface{}
	for _, song := range songsList.songs {
		if song.songGenre == genre {
			result = append(result, song)
		}
	}
	return result
}

func (songsList *SongCollection) FindByCreator(singer string) []interface{} {
	var result []interface{}
	for _, song := range songsList.songs {
		if song.singer == singer {
			result = append(result, song)
		}
	}
	return result
}
