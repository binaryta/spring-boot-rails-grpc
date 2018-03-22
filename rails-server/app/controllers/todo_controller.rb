require 'todo_services_pb.rb'
require 'todo_pb.rb'

class TodoController < ApplicationController
  def index
    stub = Com::Example::Demo::Todostore::Todostore::Stub.new(
      'localhost:50051', :this_channel_is_insecure
    )
    res = Com::Example::Demo::Todostore::ListTodosResponse.new
    @todos = res.todo
  end
end
