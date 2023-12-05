package de.dsh.backend.validators

import de.dsh.backend.errors.*
import de.dsh.backend.utils.*

import scala.concurrent.*

abstract class BaseValidator[Type, TypeUpdate](using ExecutionContext):
  def checkCanCreate(
      data: Type
  ): Future[ValidationResult]

  def checkCanUpdate(
      id: String,
      data: TypeUpdate
  ): Future[ValidationResult]

object ValidatorUtils:
  def aggregateValidationResult(
      validations: MaybeFuture[ValidationResult]*
  )(using
      ec: ExecutionContext
  ): Future[ValidationResult] =
    val futureValidations: Seq[Future[ValidationResult]] =
      validations.map:
        case future: Future[ValidationResult] => future
        case option: ValidationResult         => Future.successful(option)

    Future
      .sequence(futureValidations)
      .map: results =>
        val errors: Seq[ValidationErrorType] =
          results
            .collect:
              case ValidationResult.Invalid(errs*) => errs
            .flatten

        if (errors.isEmpty) ValidationResult.Valid
        else ValidationResult.Invalid(errors*)

enum ValidationResult:
  case Valid
  case IdNotFound
  case Invalid(errors: ValidationErrorType*)
