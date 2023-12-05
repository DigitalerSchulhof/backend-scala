package de.dsh.backend.repositories.interfaces

import de.dsh.backend.models.*
import de.dsh.backend.services.*

import scala.concurrent.*

abstract trait BaseRepository[Type, TypeUpdate]:
  def exists(
      id: String
  ): Future[Boolean]

  def search(
      options: SearchOptions[Type]
  ): Future[ListResult[WithId[Type]]]

  def get(
      id: Seq[String]
  ): Future[Seq[Option[WithId[Type]]]]

  def create(
      data: Type
  ): Future[WithId[Type]]

  def update(
      id: String,
      data: TypeUpdate,
      options: Option[UpdateOptions] = None
  ): Future[WithId[Type]]

  def delete(
      id: String,
      options: Option[DeleteOptions]
  ): Future[WithId[Type]]
