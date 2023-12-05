package de.dsh.backend.repositories.interfaces

import de.dsh.backend.models.*
import de.dsh.backend.services.*
import scala.concurrent.Future

abstract trait AccountRepository extends BaseRepository[Account, AccountUpdate]:
  def create(
      data: Account,
      options: Option[CreateAccountOptions] = None
  ): Future[WithId[Account]]
