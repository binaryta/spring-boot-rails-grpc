require 'task_services_pb.rb'
require 'task_pb.rb'

class TaskController < ApplicationController
  before_action :stub

  def index
    req = Taskstore::GetTasksRequest.new
    res = @stub.get_tasks(req)
    render json: res.task
  end

  def create
    req = Taskstore::AddTaskRequest.new(content: params["content"])
    res = @stub.add_task(req)
    render json: res.task.to_h
  end

  def update
    req = Taskstore::UpdateTaskRequest.new(id: params["id"].to_i, done: params["done"])
    res = @stub.update_task(req)
    render json: res.task.to_h
  end

  def delete
    req = Taskstore::DeleteTaskRequest.new(id: params["id"])
    res = @stub.delete_task(req)
    render json: res.http_status.to_h
  end

  private
  def stub
    @stub = Taskstore::Taskstore::Stub.new('localhost:6565', :this_channel_is_insecure)
  end
end
