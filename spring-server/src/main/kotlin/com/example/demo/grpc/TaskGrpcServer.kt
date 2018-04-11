package com.example.demo.grpc

import com.example.demo.TaskData
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
    val tasks = repository.findAll()

    tasks.forEach { task ->
      val taskBuilder = Task.newBuilder()
      taskBuilder.setId(task.id)
      taskBuilder.setContent(task.content)
      taskBuilder.setDone(task.done)
      tasksBuilder.addTask(taskBuilder)
    }
    res.onNext(tasksBuilder.build())
    res.onCompleted()
  }

  override
  fun addTask(req: AddTaskRequest, res: StreamObserver<AddTaskResponse>) {
    val task = repository.create(req.content)

    val addTaskResponseBuilder = AddTaskResponse.newBuilder()
    val taskBuilder = Task.newBuilder()
    taskBuilder.setId(task.id)
    taskBuilder.setContent(task.content)
    taskBuilder.setDone(task.done)
    addTaskResponseBuilder.setTask(taskBuilder.build())

    res.onNext(addTaskResponseBuilder.build())
    res.onCompleted()
  }

  override
  fun updateTask(req: UpdateTaskRequest, res: StreamObserver<UpdateTaskResponse>) {
    val task: TaskData? = repository.update(req.id.toInt(), !req.done)

    val updateTaskResponseBuilder = UpdateTaskResponse.newBuilder()
    val taskBuilder = Task.newBuilder()
    if (task != null) {
      taskBuilder.setId(task.id)
                 .setContent(task.content)
                 .setDone(task.done)
      updateTaskResponseBuilder.setTask(taskBuilder.build())
      res.onNext(updateTaskResponseBuilder.build())
    } else {
      res.onError(null)
    }
    res.onCompleted()
  }
}
