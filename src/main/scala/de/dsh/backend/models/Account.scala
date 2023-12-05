package de.dsh.backend.models

import de.dsh.backend.utils.*

case class Account(
    personId: String,
    username: String,
    email: String,
    password: Array[Byte],
    salt: Array[Byte],
    passwordExpiresAt: Option[DateTime],
    lastLogin: Option[DateTime],
    secondLastLogin: Option[DateTime],
    settings: AccountSettings
)

case class AccountSettings(
    emailOn: AccountSettingsNotifyOn,
    pushOn: AccountSettingsNotifyOn,
    considerNews: AccountSettingsConsiderNews,
    mailbox: AccountSettingsMailbox,
    profile: AccountSettingsProfile
)

case class AccountSettingsNotifyOn(
    newMessage: Boolean,
    newSubstitution: Boolean,
    newNews: Boolean
)

case class AccountSettingsConsiderNews(
    newEvent: Boolean,
    newBlog: Boolean,
    newGallery: Boolean,
    fileChanged: Boolean
)

case class AccountSettingsMailbox(
    deleteAfter: Option[Duration],
    deleteAfterInBin: Option[Duration]
)

case class AccountSettingsProfile(
    sessionTimeout: Duration,
    formOfAddress: FormOfAddress
)

case class AccountUpdate(
    personId: UpdateOrIgnore[String],
    username: UpdateOrIgnore[String],
    email: UpdateOrIgnore[String],
    password: UpdateOrIgnore[String],
    salt: UpdateOrIgnore[String],
    passwordExpiresAt: UpdateOrRemoveOrIgnore[DateTime],
    lastLogin: UpdateOrRemoveOrIgnore[DateTime],
    secondLastLogin: UpdateOrRemoveOrIgnore[DateTime],
    settings: AccountSettingsUpdate
)

case class AccountSettingsUpdate(
    emailOn: AccountSettingsNotifyOnUpdate,
    pushOn: AccountSettingsNotifyOnUpdate,
    considerNews: AccountSettingsConsiderNewsUpdate,
    mailbox: AccountSettingsMailboxUpdate,
    profile: AccountSettingsProfileUpdate
)

case class AccountSettingsNotifyOnUpdate(
    newMessage: UpdateOrIgnore[Boolean],
    newSubstitution: UpdateOrIgnore[Boolean],
    newNews: UpdateOrIgnore[Boolean]
)

case class AccountSettingsConsiderNewsUpdate(
    newEvent: UpdateOrIgnore[Boolean],
    newBlog: UpdateOrIgnore[Boolean],
    newGallery: UpdateOrIgnore[Boolean],
    fileChanged: UpdateOrIgnore[Boolean]
)

case class AccountSettingsMailboxUpdate(
    deleteAfter: UpdateOrRemoveOrIgnore[Duration],
    deleteAfterInBin: UpdateOrRemoveOrIgnore[Duration]
)

case class AccountSettingsProfileUpdate(
    sessionTimeout: UpdateOrIgnore[Duration],
    formOfAddress: UpdateOrIgnore[FormOfAddress]
)

enum FormOfAddress:
  case Formal
  case Informal
