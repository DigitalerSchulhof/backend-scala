package de.dsh.backend.repositories.interfaces

import de.dsh.backend.models.*

abstract trait PersonRepository extends BaseRepository[Person, PersonUpdate] {}
