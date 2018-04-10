package com.example.demo

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

object Tasks : IntIdTable("tasks") {
  val content = varchar("content", length = 100)
  val status = integer("status")
}

fun Int.toBoolean(): Boolean {
  return this != 0
}

class Task(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<Task>(Tasks)

  var content by Tasks.content
  var status by Tasks.status
}

@Repository
class ExposedTaskRepository : TaskRepository {

  companion object {
    fun convert(task: Task?): TaskData? {
      task ?: return null
      return TaskData(task.id.value.toLong(), task.content, task.status.toBoolean())
    }
  }

  override fun findById(id: Int): TaskData? {
    return transaction {
      val task = Task.findById(id)
      task ?: return@transaction null
      convert(task)
    }
  }

  override fun findAll(): List<TaskData> {
    return transaction {
      Task.all().mapNotNull(::convert)
    }
  }

  override fun create(input_content: String): TaskData {
    return transaction {
      convert(Task.new {
        content = input_content
        status = 0
      })!!
    }
  }

  override fun delete(id: Int): Boolean {
    return transaction {
      val task = Task.findById(id)
      task?.delete() != null
    }
  }

  override fun update(id: Int, content: String): TaskData? {
    return transaction {
      val task = Task.findById(id)
      task?.content = content
      convert(task)
    }
  }
}
