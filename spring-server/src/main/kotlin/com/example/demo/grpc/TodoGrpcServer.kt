package com.example.demo.grpc

import com.example.demo.todostore.*
import org.lognet.springboot.grpc.GRpcService
import io.grpc.stub.StreamObserver

@GRpcService
class TodoGrpcServer : TodostoreGrpc.TodostoreImplBase() {
  override
  fun getTodos(req: GetTodosRequest, res: StreamObserver<GetTodosResponse>) {
    val todosBuilder = GetTodosResponse.newBuilder()
    val todoBuilder1  = Todo.newBuilder()
    val todoBuilder2  = Todo.newBuilder()
    val todoBuilder3  = Todo.newBuilder()
    todoBuilder1.setId(1)
    todoBuilder1.setContent("Hello gRPC!!!")
    todoBuilder2.setId(2)
    todoBuilder2.setContent("Hello Kotlin!!!")
    todoBuilder3.setId(3)
    todoBuilder3.setContent("Hello Ruby!!!")

    todosBuilder.addTodo(todoBuilder1.build())
    todosBuilder.addTodo(todoBuilder2.build())
    todosBuilder.addTodo(todoBuilder3.build())

    res.onNext(todosBuilder.build())
    res.onCompleted()
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
