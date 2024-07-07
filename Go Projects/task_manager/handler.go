package main

import (
	"encoding/json"
	"net/http"
)

var taskStorage = newInMemoryStorage()

func handleListTasks(writer http.ResponseWriter, _ *http.Request) {
	taskList := taskStorage.listTasks()
	err := json.NewEncoder(writer).Encode(taskList)

	if err != nil {
		return
	}
}

func handleAddTask(writer http.ResponseWriter, request *http.Request) {
	taskDescription := request.URL.Query().Get("description")

	if taskDescription == "" {
		respondWithError(
			writer, http.StatusBadRequest, "Missing task description",
		)
		return
	}

	newTask := taskStorage.addTask(taskDescription)
	err := json.NewEncoder(writer).Encode(newTask)

	if err != nil {
		return
	}
}

func handleCompleteTask(writer http.ResponseWriter, request *http.Request) {
	taskId, err := parseTaskId(request)

	if err != nil {
		respondWithError(
			writer, http.StatusBadRequest, "Invalid task id",
		)
		return
	}

	if !taskStorage.completeTask(taskId) {
		respondWithError(
			writer, http.StatusNotFound, "Task not found",
		)
		return
	}

	writer.WriteHeader(http.StatusOK)
}

func handleRemoveTask(writer http.ResponseWriter, request *http.Request) {
	taskId, err := parseTaskId(request)

	if err != nil {
		respondWithError(
			writer, http.StatusBadRequest, "Invalid task id",
		)
		return
	}

	if !taskStorage.removeTask(taskId) {
		respondWithError(
			writer, http.StatusNotFound, "Task not found",
		)
		return
	}

	writer.WriteHeader(http.StatusOK)
}
