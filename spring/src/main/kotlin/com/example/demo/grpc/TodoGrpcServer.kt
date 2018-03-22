package com.example.demo.grpc

import com.example.demo.todostore.*
import org.lognet.springboot.grpc.GRpcService
import io.grpc.stub.StreamObserver

@GRpcService
class TodoGrpcServer : TodostoreGrpc.TodostoreImplBase() {

  override
  fun listTodos(req: ListTodosRequest, res: StreamObserver<ListTodosResponse>) {
    val todosBuilder = ListTodosResponse.newBuilder()
    val todoBuilder  = GetTodoResponse.newBuilder()
    todoBuilder.setId(1)
    todoBuilder.setContent("Hello gRPC!!!")

    todosBuilder.setTodo(1, todoBuilder.build())
    res.onNext(todosBuilder.build())
    res.onCompleted()
  }
}
