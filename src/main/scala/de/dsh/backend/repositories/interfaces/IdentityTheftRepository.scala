package de.dsh.backend.repositories.interfaces

import de.dsh.backend.models.*

abstract trait IdentityTheftRepository
    extends BaseRepository[IdentityTheft, IdentityTheftUpdate] {}
