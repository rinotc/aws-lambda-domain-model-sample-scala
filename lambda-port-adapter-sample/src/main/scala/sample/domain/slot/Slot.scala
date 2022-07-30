package sample.domain.slot

import arch.ddd.{Entity, Id}
import sample.domain.util.Location

import java.time.LocalDate

class Slot(
    val id: Id[Slot],
    val reservationDate: LocalDate,
    val location: Location
) extends Entity[Slot] {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Slot]

  override def equals(other: Any): Boolean = other match {
    case that: Slot => (that canEqual this) && id == that.id
    case _          => false
  }

  override def hashCode(): Int = 31 * id.##
}
