require 'task_services_pb.rb'
require 'task_pb.rb'

class TaskController < ApplicationController
  before_action :stub

  def index
    req = Taskstore::GetTasksResponse.new
    res = @stub.get_tasks(req)
    @tasks = res.task
  end

  private
  def stub
    @stub = Taskstore::Taskstore::Stub.new('localhost:6565', :this_channel_is_insecure)
  end
end
