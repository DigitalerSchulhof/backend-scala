package de.dsh.backend.repositories.interfaces

import de.dsh.backend.models.*

abstract trait SessionRepository
    extends BaseRepository[Session, SessionUpdate] {}
