package sample.domain.recipient

import arch.ddd.{Entity, Id}
import sample.domain.slot.Slot
import sample.domain.util.{Age, EmailAddress}

/**
 * 受信者情報
 *
 * @param id        受信者のID
 * @param email     メールアドレス
 * @param firstName 名
 * @param lastName  氏
 * @param age       年齢
 * @param slots     スロットのリスト
 */
final class Recipient(
    val id: Id[Recipient],
    val email: EmailAddress,
    val firstName: String,
    val lastName: String,
    val age: Age,
    val slots: Seq[Slot]
) extends Entity[Recipient] {

  def areSlotsSameDate(slot: Slot): Boolean =
    slots.exists(_.reservationDate == slot.reservationDate)

  def isSlotCountsOverThanEqualTwo: Boolean = slots.lengthIs >= 2

  def addReserveSlot(slot: Slot): Either[String, Recipient] = {
    for {
      _ <- Either.cond(areSlotsSameDate(slot), (), "Slot with the same date already exists.")
      _ <- Either.cond(isSlotCountsOverThanEqualTwo, (), "slot count over 2")
    } yield copy(slots = this.slots.appended(slot))
  }

  override def equals(other: Any): Boolean = other match {
    case that: Recipient => id == that.id
    case _               => false
  }

  override def hashCode(): Int = 31 * id.##

  override def toString =
    s"Recipient(id=$id, email=$email, firstName=$firstName, lastName=$lastName, age=$age, slots=$slots)"

  private def copy(
      id: Id[Recipient] = this.id,
      email: EmailAddress = this.email,
      firstName: String = this.firstName,
      lastName: String = this.lastName,
      age: Age = this.age,
      slots: Seq[Slot] = this.slots
  ) = new Recipient(id, email, firstName, lastName, age, slots)
}
