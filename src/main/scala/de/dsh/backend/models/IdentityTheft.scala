package de.dsh.backend.models

import de.dsh.backend.utils.*

case class IdentityTheft(
    personId: String,
    reportedAt: DateTime
)

case class IdentityTheftUpdate(
    personId: UpdateOrIgnore[String],
    reportedAt: UpdateOrIgnore[DateTime]
)
