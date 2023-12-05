package de.dsh.backend.permissions

import scala.concurrent.*

class ClassPermissionHandler(using ExecutionContext)
    extends BasePermissionHandler:
  def checkMaySearch(): Future[PermissionResult] =
    ???

  def checkMayGet(): Future[PermissionResult] =
    ???

  def checkMayCreate(): Future[PermissionResult] =
    ???

  def checkMayUpdate(): Future[PermissionResult] =
    ???

  def checkMayDelete(): Future[PermissionResult] =
    ???
