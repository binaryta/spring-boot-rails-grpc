package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.jetbrains.exposed.sql.Database

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
  Database.connect("jdbc:mysql://localhost/todo", "com.mysql.jdbc.Driver", "root", "")
  runApplication<DemoApplication>(*args)
}
