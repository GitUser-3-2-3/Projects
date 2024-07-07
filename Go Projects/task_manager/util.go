package main

import (
	"net/http"
	"strconv"
)

func respondWithError(writer http.ResponseWriter, statusCode int, errorMessage string) {
	writer.WriteHeader(statusCode)
	_, err := writer.Write([]byte(errorMessage))

	if err != nil {
		return
	}
}

func parseTaskId(request *http.Request) (int, error) {
	taskId := request.URL.Query().Get("id")
	return strconv.Atoi(taskId)
}
