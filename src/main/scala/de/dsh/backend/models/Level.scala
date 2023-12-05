package de.dsh.backend.models

case class Level(
    schoolyearId: String,
    name: String
)

case class LevelUpdate(
    schoolyearId: UpdateOrIgnore[String],
    name: UpdateOrIgnore[String]
)
