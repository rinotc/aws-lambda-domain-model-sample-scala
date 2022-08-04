package sample.port.recipient

import arch.ddd.Id
import sample.domain.recipient.Recipient

trait IRecipientOutputPort {

  def getRecipientById(recipientId: Id[Recipient]): Option[Recipient]

  def addReservation(recipient: Recipient): Either[String, Unit]
}
