package de.dsh.backend.models

import de.dsh.backend.utils.*

enum UpdateOrIgnore[+T]:
  final val toOption: Option[T] = this match
    case Ignore                   => None
    case UpdateOrIgnore.Update(v) => Some(v)

  case Ignore
  case Update(value: T)

  export toOption.{map, getOrElse}

enum UpdateOrRemoveOrIgnore[+T]:
  final val toOption: Option[Option[T]] = this match
    case Ignore                           => None
    case Remove                           => Some(None)
    case UpdateOrRemoveOrIgnore.Update(v) => Some(Some(v))

  case Ignore
  case Remove
  case Update(value: T)

  export toOption.{map, getOrElse}

case class WithId[M](
    id: String,
    rev: String,
    createdAt: DateTime,
    updatedAt: DateTime,
    private val model: M
) {}

object WithId:
  given [M]: Conversion[WithId[M], M] with
    def apply(withId: WithId[M]): M = withId.model
