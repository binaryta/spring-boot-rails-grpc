require 'todo_services_pb.rb'
require 'todo_pb.rb'

class TodoController < ApplicationController
  def index
    stub = Todostore::Todostore::Stub.new(
      'localhost:6565', :this_channel_is_insecure
    )
    req = Todostore::GetTodosResponse.new
    res = stub.get_todos(req)
    @todos = res.todo
  end
end
