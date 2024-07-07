package main

import (
	"fmt"
	"log"
	"net/http"
)

func main() {
	http.HandleFunc("/tasks", handleListTasks)
	http.HandleFunc("/add", handleAddTask)
	http.HandleFunc("/complete", handleCompleteTask)
	http.HandleFunc("/remove", handleRemoveTask)

	fmt.Println("Starting server on port :8080")
	log.Fatal(http.ListenAndServe(":8080", nil))
}
