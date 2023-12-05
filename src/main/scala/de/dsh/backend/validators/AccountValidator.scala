package de.dsh.backend.validators

import de.dsh.backend.errors.*
import de.dsh.backend.models.*
import de.dsh.backend.repositories.interfaces.*
import de.dsh.backend.utils.*

import scala.concurrent.*

import ValidationResult.*
import ValidatorUtils.*

val MaxSessionTimeout = Duration(minutes = 5)

class AccountValidator(
    repository: AccountRepository,
    personRepository: PersonRepository
)(using ExecutionContext)
    extends BaseValidator[Account, AccountUpdate]:
  def checkCanCreate(
      data: Account
  ): Future[ValidationResult] =
    aggregateValidationResult(
      checkPersonExists(data.personId),
      checkSettingsValid(data.settings)
    )

  def checkCanUpdate(
      id: String,
      data: AccountUpdate
  ): Future[ValidationResult] =
    repository
      .exists(id)
      .flatMap:
        case false => Future.successful(IdNotFound)
        case true =>
          aggregateValidationResult(
            data.personId.map(checkPersonExists).getOrElse(Valid),
            checkSettingsValid(data.settings)
          )

  private def checkPersonExists(
      personId: String
  ): Future[ValidationResult] =
    personRepository
      .exists(personId)
      .map:
        if (_) Valid
        else
          Invalid(ValidationErrorTypes.Account.PersonDoesNotExist)

  private def checkSettingsValid(
      settings: AccountSettings | AccountSettingsUpdate
  ): Future[ValidationResult] =
    def checkDeleteAfter(
        deleteAfter: Option[Duration]
    ): ValidationResult = deleteAfter match
      case Some(Duration.Instant) =>
        Invalid(ValidationErrorTypes.Account.SettingsMailboxDeleteAfterTooLow)
      case _ => Valid

    def checkDeleteAfterInBin(
        deleteAfterInBin: Option[Duration]
    ): ValidationResult = deleteAfterInBin match
      case Some(Duration.Instant) =>
        Invalid(
          ValidationErrorTypes.Account.SettingsMailboxDeleteAfterInBinTooLow
        )
      case _ => Valid

    def checkSessionTimeout(
        sessionTimeout: Duration
    ): ValidationResult = sessionTimeout match
      case Duration.Instant =>
        Invalid(
          ValidationErrorTypes.Account.SettingsProfileSessionTimeoutTooLow
        )
      case t if t > MaxSessionTimeout =>
        Invalid(
          ValidationErrorTypes.Account.SettingsProfileSessionTimeoutTooHigh
        )
      case _ => Valid

    settings match
      case settings: AccountSettings =>
        aggregateValidationResult(
          checkDeleteAfter(settings.mailbox.deleteAfter),
          checkDeleteAfterInBin(settings.mailbox.deleteAfterInBin),
          checkSessionTimeout(settings.profile.sessionTimeout)
        )

      case settings: AccountSettingsUpdate =>
        aggregateValidationResult(
          settings.mailbox.deleteAfter
            .map(checkDeleteAfter)
            .getOrElse(Valid),
          settings.mailbox.deleteAfterInBin
            .map(checkDeleteAfterInBin)
            .getOrElse(Valid),
          settings.profile.sessionTimeout
            .map(checkSessionTimeout)
            .getOrElse(Valid)
        )
