package de.dsh.backend.models

import de.dsh.backend.utils.*

case class Schoolyear(
    name: String,
    start: Date,
    end: Date
)

case class SchoolyearUpdate(
    name: UpdateOrIgnore[String],
    start: UpdateOrIgnore[Date],
    end: UpdateOrIgnore[Date]
)
