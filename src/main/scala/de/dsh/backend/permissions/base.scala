package de.dsh.backend.permissions

import scala.concurrent.*

abstract class BasePermissionHandler(using ExecutionContext):
  def checkMaySearch(): Future[PermissionResult]
  def checkMayGet(): Future[PermissionResult]
  def checkMayCreate(): Future[PermissionResult]
  def checkMayUpdate(): Future[PermissionResult]
  def checkMayDelete(): Future[PermissionResult]

enum PermissionResult:
  case Allowed
  case Denied(missing: String)
