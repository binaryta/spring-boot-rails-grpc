package com.example.demo.grpc

import com.example.demo.ExposedTaskRepository
import com.example.demo.TaskRepository
import com.example.demo.taskstore.*
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired;
import io.grpc.stub.StreamObserver

@GRpcService
class TaskGrpcServer(@Autowired repository: TaskRepository) : TaskstoreGrpc.TaskstoreImplBase() {
  val repository = repository

  override
  fun getTasks(req: GetTasksRequest, res: StreamObserver<GetTasksResponse>) {
    val tasksBuilder = GetTasksResponse.newBuilder()
    val repository = ExposedTaskRepository()
    val tasks = repository.findAll()

    tasks.forEach { task ->
      val taskBuilder = Task.newBuilder()
      taskBuilder.setId(task.id)
      taskBuilder.setContent(task.content)
      taskBuilder.setStatus(task.status)
      tasksBuilder.addTask(taskBuilder)
    }
    res.onNext(tasksBuilder.build())
    res.onCompleted()
  }

  override
  fun addTask(req: AddTaskRequest, res: StreamObserver<AddTaskResponse>) {
  }

  //override
  //fun getTask(req: GetTaskRequest, res: StreamObserver<GetTaskResponse>) {
  //  val getTaskBuilder = GetTaskResponse.newBuilder()
  //  val taskBuilder  = Task.newBuilder()
  //  taskBuilder.setId(1)
  //  taskBuilder.setContent("Hello gRPC!!!")

  //  getTaskBuilder.setTask(taskBuilder.build())
  //  res.onNext(getTaskBuilder.build())
  //  res.onCompleted()
  //}
}
