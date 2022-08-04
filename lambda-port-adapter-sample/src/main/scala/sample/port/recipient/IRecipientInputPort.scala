package sample.port.recipient

import arch.ddd.Id
import sample.domain.recipient.Recipient
import sample.domain.slot.Slot
import sample.domain.util.Status

trait IRecipientInputPort {

  def makeReservation(recipientId: Id[Recipient], slotId: Id[Slot]): Status
}
