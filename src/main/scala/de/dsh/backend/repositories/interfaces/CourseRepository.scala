package de.dsh.backend.repositories.interfaces

import de.dsh.backend.models.*

abstract trait CourseRepository extends BaseRepository[Course, CourseUpdate] {}
