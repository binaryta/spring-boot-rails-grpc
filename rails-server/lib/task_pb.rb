# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: task.proto

require 'google/protobuf'

Google::Protobuf::DescriptorPool.generated_pool.build do
  add_message "taskstore.GetTasksRequest" do
  end
  add_message "taskstore.GetTasksResponse" do
    repeated :task, :message, 1, "taskstore.Task"
  end
  add_message "taskstore.AddTaskRequest" do
    optional :content, :string, 1
  end
  add_message "taskstore.AddTaskResponse" do
    optional :task, :message, 1, "taskstore.Task"
  end
  add_message "taskstore.UpdateTaskRequest" do
    optional :id, :int64, 1
    optional :done, :bool, 2
  end
  add_message "taskstore.UpdateTaskResponse" do
    optional :task, :message, 1, "taskstore.Task"
  end
  add_message "taskstore.DeleteTaskRequest" do
    optional :id, :int64, 1
  end
  add_message "taskstore.DeleteTaskResponse" do
    optional :httpStatus, :bool, 1
  end
  add_message "taskstore.Task" do
    optional :id, :int64, 1
    optional :content, :string, 2
    optional :done, :bool, 3
  end
end

module Taskstore
  GetTasksRequest = Google::Protobuf::DescriptorPool.generated_pool.lookup("taskstore.GetTasksRequest").msgclass
  GetTasksResponse = Google::Protobuf::DescriptorPool.generated_pool.lookup("taskstore.GetTasksResponse").msgclass
  AddTaskRequest = Google::Protobuf::DescriptorPool.generated_pool.lookup("taskstore.AddTaskRequest").msgclass
  AddTaskResponse = Google::Protobuf::DescriptorPool.generated_pool.lookup("taskstore.AddTaskResponse").msgclass
  UpdateTaskRequest = Google::Protobuf::DescriptorPool.generated_pool.lookup("taskstore.UpdateTaskRequest").msgclass
  UpdateTaskResponse = Google::Protobuf::DescriptorPool.generated_pool.lookup("taskstore.UpdateTaskResponse").msgclass
  DeleteTaskRequest = Google::Protobuf::DescriptorPool.generated_pool.lookup("taskstore.DeleteTaskRequest").msgclass
  DeleteTaskResponse = Google::Protobuf::DescriptorPool.generated_pool.lookup("taskstore.DeleteTaskResponse").msgclass
  Task = Google::Protobuf::DescriptorPool.generated_pool.lookup("taskstore.Task").msgclass
end
