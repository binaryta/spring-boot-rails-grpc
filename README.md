# spring-boot-rails-grpc

<img src="https://github.com/Tnarita0000/spring-boot-rails-grpc/blob/master/screen.png?raw=true" width="600" />


gRPCによるKotlin & Ruby & Reactという構成でのWeb運用 <前編>  
http://blog.engineer.adways.net/entry/2018/03/23/190820

gRPCによるKotlin & Ruby & Reactという構成でのWeb運用 <後編>
http://blog.engineer.adways.net/entry/2018/04/13/130000

## Get started
Create grpc-demo database and create tasks table into it on MySQL, before try this demo.
```sql
CREATE DATABASE grpc-demo;

CREATE TABLE `tasks` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL DEFAULT '',
  `done` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
```

## Spring server (gRPC server)
Execute gradle task `generateProto` and `bootRun`.
```
$ cd spring-server/
$ ./gradlew generateProto bootRun
```

## Rails server (View rendering server)

Generate protocol buffer, then run this demo application.
```
$ cd rails-server/
$ bundle exec grpc_tools_ruby_protoc -I ../proto --ruby_out=lib --grpc_out=lib ../proto/task.proto
$ rails s -b 0.0.0.0
```

Build frontend javascript files.
```
$ cd frontend/
$ yarn build:dev
```

Access http://localhost:3000/
