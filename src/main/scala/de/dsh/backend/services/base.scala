package de.dsh.backend.services

import cats.data.*
import de.dsh.backend.errors.*
import de.dsh.backend.models.*
import de.dsh.backend.permissions.*
import de.dsh.backend.validators.*

import scala.concurrent.*

type Result[T] = Either[ClientError, T]
type FutureResult[T] = Future[Result[T]]

abstract class BaseService[Type, TypeUpdate](using ExecutionContext):
  given [A]: Conversion[EitherT[Future, ClientError, A], FutureResult[A]] with
    def apply(e: EitherT[Future, ClientError, A]): FutureResult[A] =
      e.value

  def search(
      options: SearchOptions[Type]
  ): FutureResult[ListResult[WithId[Type]]]

  def get(
      id: Seq[String]
  ): FutureResult[Seq[Option[WithId[Type]]]]

  def create(
      data: Type
  ): FutureResult[WithId[Type]]

  def update(
      id: String,
      data: TypeUpdate,
      options: Option[UpdateOptions]
  ): FutureResult[WithId[Type]]

  def delete(
      id: String,
      options: Option[DeleteOptions]
  ): FutureResult[WithId[Type]]

  protected def checkPermission(
      permissionCheck: Future[PermissionResult]
  ): EitherT[Future, ClientError, Unit] = EitherT(
    permissionCheck map:
      case PermissionResult.Allowed         => Right(())
      case PermissionResult.Denied(missing) => Left(PermissionError(missing))
  )

  protected def checkValidator(
      validationCheck: Future[ValidationResult]
  ): EitherT[Future, ClientError, Unit] = EitherT(
    validationCheck map:
      case ValidationResult.Valid            => Right(())
      case ValidationResult.IdNotFound       => Left(IdNotFoundError)
      case ValidationResult.Invalid(errors*) => Left(ValidationError(errors*))
  )

case class UpdateOptions(
    ifRev: Option[String]
)

case class DeleteOptions(
    ifRev: Option[String]
)

case class ListResult[M](
    total: Int,
    items: Seq[M]
)

case class SearchOptions[M](
    limit: Option[Int],
    offset: Option[Int],
    filter: Filter,
    order: Option[String]
)

sealed trait Filter

case class OrFilter(
    filters: Seq[Filter]
) extends Filter

case class AndFilter(
    filters: Seq[Filter]
) extends Filter

case class FilterImpl(
    field: String,
    operator: String,
    value: String
) extends Filter
