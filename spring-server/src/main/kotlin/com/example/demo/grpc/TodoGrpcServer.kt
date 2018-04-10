package com.example.demo.grpc

import com.example.demo.ExposedTaskRepository
import com.example.demo.TaskRepository
import com.example.demo.todostore.*
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired;
import io.grpc.stub.StreamObserver

@GRpcService
class TodoGrpcServer(@Autowired repository: TaskRepository) : TodostoreGrpc.TodostoreImplBase() {
  val repository = repository

  override
  fun getTodos(req: GetTodosRequest, res: StreamObserver<GetTodosResponse>) {
    val todosBuilder = GetTodosResponse.newBuilder()
    val repository = ExposedTaskRepository()
    val tasks = repository.findAll()

    tasks.forEach { task ->
      val todoBuilder = Todo.newBuilder()
      todoBuilder.setId(task.id)
      todoBuilder.setContent(task.content)
      todoBuilder.setStatus(task.status)
      todosBuilder.addTodo(todoBuilder)
    }
    res.onNext(todosBuilder.build())
    res.onCompleted()
  }

  override
  fun addTodo(req: AddTodoRequest, res: StreamObserver<AddTodoResponse>) {
  }

  //override
  //fun getTodo(req: GetTodoRequest, res: StreamObserver<GetTodoResponse>) {
  //  val getTodoBuilder = GetTodoResponse.newBuilder()
  //  val todoBuilder  = Todo.newBuilder()
  //  todoBuilder.setId(1)
  //  todoBuilder.setContent("Hello gRPC!!!")

  //  getTodoBuilder.setTodo(todoBuilder.build())
  //  res.onNext(getTodoBuilder.build())
  //  res.onCompleted()
  //}
}
