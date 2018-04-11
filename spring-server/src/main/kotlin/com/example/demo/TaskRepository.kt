package com.example.demo

interface TaskRepository {
  fun findById(id: Int): TaskData?
  fun findAll(): List<TaskData>
  fun create(input_content: String): TaskData
  fun delete(id: Int): Boolean
  fun update(id: Int, done: Boolean): TaskData?
}
