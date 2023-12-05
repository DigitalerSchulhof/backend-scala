package de.dsh.backend.services

import cats.data.*
import de.dsh.backend.errors.*
import de.dsh.backend.models.*
import de.dsh.backend.permissions.*
import de.dsh.backend.repositories.interfaces.*
import de.dsh.backend.validators.*

import scala.concurrent.*

class AccountService(
    repository: AccountRepository,
    permissionHandler: AccountPermissionHandler,
    validator: AccountValidator
)(using ExecutionContext)
    extends BaseService[Account, AccountUpdate]:
  override def search(
      options: SearchOptions[Account]
  ): FutureResult[ListResult[WithId[Account]]] =
    for
      _ <- checkPermission(permissionHandler.checkMaySearch())
      result <- EitherT.right(repository.search(options))
    yield result

  override def get(
      id: Seq[String]
  ): FutureResult[Seq[Option[WithId[Account]]]] =
    for
      _ <- checkPermission(permissionHandler.checkMayGet())
      result <- EitherT.right(repository.get(id))
    yield result

  override def create(data: Account): FutureResult[WithId[Account]] =
    create(data, None)

  def create(
      data: Account,
      options: Option[CreateAccountOptions]
  ): FutureResult[WithId[Account]] =
    for
      _ <- checkPermission(permissionHandler.checkMayCreate())
      _ <- checkValidator(validator.checkCanCreate(data))
      result <- EitherT.right(repository.create(data, options))
    yield result

  override def update(
      id: String,
      data: AccountUpdate,
      options: Option[UpdateOptions]
  ): FutureResult[WithId[Account]] =
    for
      _ <- checkPermission(permissionHandler.checkMayUpdate())
      _ <- checkValidator(validator.checkCanUpdate(id, data))
      result <- EitherT.right(repository.update(id, data, options))
    yield result

  override def delete(
      id: String,
      options: Option[DeleteOptions]
  ): FutureResult[WithId[Account]] =
    for
      _ <- checkPermission(permissionHandler.checkMayDelete())
      result <- EitherT.right(repository.delete(id, options))
    yield result

case class CreateAccountOptions(
    ifPersonRev: Option[String]
)
