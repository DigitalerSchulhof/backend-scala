package de.dsh.backend.models

import de.dsh.backend.utils.*

case class Session(
    accountId: String,
    issuedAt: DateTime,
    didShowLastLogin: Boolean
)

case class SessionUpdate(
    accountId: UpdateOrIgnore[String],
    issuedAt: UpdateOrIgnore[DateTime],
    didShowLastLogin: UpdateOrIgnore[Boolean]
)
