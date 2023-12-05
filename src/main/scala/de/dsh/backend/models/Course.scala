package de.dsh.backend.models

case class Course(
    classId: String,
    name: String
)

case class CourseUpdate(
    classId: UpdateOrIgnore[String],
    name: UpdateOrIgnore[String]
)
