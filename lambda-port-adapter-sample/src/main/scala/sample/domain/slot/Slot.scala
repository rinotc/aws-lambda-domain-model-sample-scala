package sample.domain.slot

import arch.ddd.{Entity, Id}
import sample.domain.util.Location
import software.amazon.awssdk.services.dynamodb.model.AttributeValue

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Slot private (
    val id: Id[Slot],
    val reservationDate: LocalDateTime,
    val location: Location,
    val isVacant: Boolean
) extends Entity[Slot] {

  import Slot._

  def forDynamoDbMap: Map[String, AttributeValue] = Map(
    "pk"               -> AttributeValue.builder().s(primaryKeyPrefix + id.value.toString).build(),
    "reservation_date" -> AttributeValue.builder().s(reservationDate.format(dateTimeFormatter)).build(),
    "location"         -> AttributeValue.builder().s(location.value).build()
  )

  def canEqual(other: Any): Boolean = other.isInstanceOf[Slot]

  override def equals(other: Any): Boolean = other match {
    case that: Slot => (that canEqual this) && id == that.id
    case _          => false
  }

  override def hashCode(): Int = 31 * id.##
}

object Slot {

  final val dateTimeFormatter = DateTimeFormatter.ISO_INSTANT

  final val primaryKeyPrefix = "slot#"

  def apply(id: Id[Slot], reservationDate: LocalDateTime, location: Location) =
    new Slot(id, reservationDate, location, isVacant = true)
}
