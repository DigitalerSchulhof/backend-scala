package de.dsh.backend.repositories.interfaces

import de.dsh.backend.models.*

abstract trait SchoolyearRepository
    extends BaseRepository[Schoolyear, SchoolyearUpdate] {}
