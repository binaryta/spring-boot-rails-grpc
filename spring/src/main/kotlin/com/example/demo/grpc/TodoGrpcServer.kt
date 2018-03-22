package com.example.demo.grpc

import com.example.demo.todostore.*
import org.lognet.springboot.grpc.GRpcService
import io.grpc.stub.StreamObserver

@GRpcService
class TodoGrpcServer : TodostoreGrpc.TodostoreImplBase() {
  override
  fun listTodos(req: ListTodosRequest, res: StreamObserver<ListTodosResponse>) {
    val todosBuilder = ListTodosResponse.newBuilder()
    val todoBuilder1  = GetTodoResponse.newBuilder()
    val todoBuilder2  = GetTodoResponse.newBuilder()
    val todoBuilder3  = GetTodoResponse.newBuilder()
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

  override
  fun getTodo(req: GetTodoRequest, res: StreamObserver<GetTodoResponse>) {
    val todoBuilder  = GetTodoResponse.newBuilder()
    todoBuilder.setId(1)
    todoBuilder.setContent("Hello gRPC!!!")
    res.onNext(todoBuilder.build())
    res.onCompleted()
  }

}
