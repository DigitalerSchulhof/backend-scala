package de.dsh.backend.models

case class Person(
    name: Person.Name,
    `type`: PersonType,
    gender: PersonGender,
    teacherCode: Option[String],
    accountId: Option[String]
)

case class PersonUpdate(
    name: UpdateOrIgnore[Person.Name],
    `type`: UpdateOrIgnore[PersonType],
    gender: UpdateOrIgnore[PersonGender],
    teacherCode: UpdateOrRemoveOrIgnore[String],
    accountId: UpdateOrRemoveOrIgnore[String]
)

object Person:
  case class Name(
      firstname: String,
      lastname: String
  )

enum PersonType:
  case Student, Teacher, Parent, Admin, Other

enum PersonGender:
  case Male, Female, Other
