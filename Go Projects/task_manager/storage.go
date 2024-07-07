package main

import "sync"

type InMemoryStorage struct {
	sync.Mutex
	tasks      map[int]Task
	nextTaskId int
}

func newInMemoryStorage() *InMemoryStorage {
	return &InMemoryStorage{
		tasks:      make(map[int]Task),
		nextTaskId: 1,
	}
}

func (storage *InMemoryStorage) addTask(description string) Task {
	storage.Lock()
	defer storage.Unlock()

	newTask := Task{
		Id:          storage.nextTaskId,
		Description: description,
		Completed:   false,
	}

	storage.tasks[storage.nextTaskId] = newTask
	storage.nextTaskId++
	return newTask
}

func (storage *InMemoryStorage) listTasks() []Task {
	storage.Lock()
	defer storage.Unlock()

	taskList := make([]Task, 0, len(storage.tasks))
	for _, task := range storage.tasks {
		taskList = append(taskList, task)
	}
	return taskList
}

func (storage *InMemoryStorage) completeTask(taskId int) bool {
	storage.Lock()
	defer storage.Unlock()

	task, exists := storage.tasks[taskId]
	if !exists {
		return false
	}

	task.Completed = true
	storage.tasks[taskId] = task
	return true
}

func (storage *InMemoryStorage) removeTask(taskId int) bool {
	storage.Lock()
	defer storage.Unlock()

	if _, exists := storage.tasks[taskId]; !exists {
		return false
	}

	delete(storage.tasks, taskId)
	return true
}
