package de.dsh.backend.models

case class Class(
    levelId: String,
    name: String
)

case class ClassUpdate(
    levelId: UpdateOrIgnore[String],
    name: UpdateOrIgnore[String]
)
