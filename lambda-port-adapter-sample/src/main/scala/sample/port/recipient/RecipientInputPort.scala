package sample.port.recipient
import arch.ddd.Id
import sample.domain.recipient.Recipient
import sample.domain.slot.Slot
import sample.domain.util.Status
import sample.port.slot.ISlotOutputPort

import javax.inject.Inject

final class RecipientInputPort @Inject() (recipientOutputPort: IRecipientOutputPort, slotOutputPort: ISlotOutputPort)
    extends IRecipientInputPort {

  override def makeReservation(recipientId: Id[Recipient], slotId: Id[Slot]): Status = {
    val result = for {
      recipient <- recipientOutputPort
        .getRecipientById(recipientId).toRight(Status(400, "Request instance is not found. Something wrong!"))
      slot <- slotOutputPort.getSlotById(slotId).toRight(Status(400, "Request instance is not found. Something wrong!"))
      addedRecipient <- recipient.addReserveSlot(slot).left.map { _ =>
        Status(200, "The recipient's reservation is NOT added!")
      }
      _ <- recipientOutputPort.addReservation(addedRecipient).left.map(e => Status(500, e))
    } yield {
      println(s"recipient: ${recipient.firstName}, slot date: ${slot.reservationDate}")
      Status(200, "The recipient's reservation is added.")
    }

    result.unwrap
  }
}
