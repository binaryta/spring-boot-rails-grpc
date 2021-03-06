Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  get "/" => "home#index"
  get   "/tasks"      => "task#index"
  post  "/tasks"      => "task#create"
  patch "/tasks/:id"  => "task#update"
  delete "/tasks/:id" => "task#delete"
end
