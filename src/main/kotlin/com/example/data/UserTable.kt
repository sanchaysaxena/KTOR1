package com.example.data

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.util.UUID

object UserTable:Table() {
    val id:Column<UUID> = uuid("id")
    val userName:Column<String> = varchar("UserName",512)
    val password:Column<String> = varchar("Password",512)

    override val primaryKey:PrimaryKey=PrimaryKey(id)
}