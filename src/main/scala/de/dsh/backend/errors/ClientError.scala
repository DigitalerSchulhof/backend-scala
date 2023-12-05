package de.dsh.backend.errors

sealed abstract class ClientError()

case object IdNotFoundError extends ClientError()

case object RevMismatchError extends ClientError()

sealed case class PermissionError(permission: String) extends ClientError()

sealed case class ValidationError(types: ValidationErrorType*)
    extends ClientError()

sealed trait ValidationErrorType(val code: String)

object ValidationErrorTypes:
  object Account:
    case object PersonDoesNotExist
        extends ValidationErrorType("PERSON_DOES_NOT_EXIST")
    case object SettingsMailboxDeleteAfterTooLow
        extends ValidationErrorType("DELETE_AFTER_TOO_LOW")
    case object SettingsMailboxDeleteAfterInBinTooLow
        extends ValidationErrorType("DELETE_AFTER_IN_BIN_TOO_LOW")
    case object SettingsProfileSessionTimeoutTooLow
        extends ValidationErrorType("SESSION_TIMEOUT_TOO_LOW")
    case object SettingsProfileSessionTimeoutTooHigh
        extends ValidationErrorType("SESSION_TIMEOUT_TOO_HIGH")
  object Class:
    case object LevelDoesNotExist
        extends ValidationErrorType("LEVEL_DOES_NOT_EXIST")
  object Course:
    case object ClassDoesNotExist
        extends ValidationErrorType("CLASS_DOES_NOT_EXIST")
