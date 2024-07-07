package main

import "sync"

type Task struct {
	Id          int
	Description string
	Completed   bool
}

type TaskManager struct {
	sync.Mutex
	tasks      []Task
	nextTaskId int
}

func _() *TaskManager {
	return &TaskManager{
		tasks:      []Task{},
		nextTaskId: 1,
	}
}

func (manager *TaskManager) addTask(description string) Task {
	manager.Lock()
	defer manager.Unlock()

	newTask := Task{
		Id:          manager.nextTaskId,
		Description: description,
		Completed:   false,
	}

	manager.tasks = append(manager.tasks, newTask)
	manager.nextTaskId++
	return newTask
}

func (manager *TaskManager) listTasks() []Task {
	manager.Lock()
	defer manager.Unlock()

	return manager.tasks
}

func (manager *TaskManager) completeTask(taskId int) bool {
	manager.Lock()
	defer manager.Unlock()

	for index, task := range manager.tasks {
		if task.Id == taskId {
			manager.tasks[index].Completed = true
			return true
		}
	}
	return false
}

func (manager *TaskManager) removeTask(taskId int) bool {
	manager.listTasks()
	defer manager.Unlock()

	for index, task := range manager.tasks {
		if task.Id == taskId {
			manager.tasks = append(
				manager.tasks[:index],
				manager.tasks[index+1:]...,
			)
			return true
		}
	}
	return false
}
