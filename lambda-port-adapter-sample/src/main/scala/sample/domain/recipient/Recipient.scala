package sample.domain.recipient

import arch.ddd.{Entity, Id}
import sample.domain.slot.Slot
import sample.domain.util.{Age, EmailAddress}

final class Recipient(
    val id: Id[Recipient],
    val email: EmailAddress,
    val firstName: String,
    val lastName: String,
    val age: Age
) extends Entity[Recipient] {

  override def equals(other: Any): Boolean = other match {
    case that: Recipient => id == that.id
    case _               => false
  }

  override def hashCode(): Int = 31 * id.##

  override def toString = s"Recipient(id=$id, email=$email, firstName=$firstName, lastName=$lastName, age=$age)"
}
