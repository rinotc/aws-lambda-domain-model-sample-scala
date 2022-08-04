package sample.port.recipient
import arch.ddd.Id
import sample.adapter.recipient.IRecipientAdapter
import sample.domain.recipient.Recipient

import javax.inject.Inject

class RecipientOutputPort @Inject() (recipientAdapter: IRecipientAdapter) extends IRecipientOutputPort {

  override def getRecipientById(recipientId: Id[Recipient]): Option[Recipient] = recipientAdapter.load(recipientId)

  override def addReservation(recipient: Recipient): Either[String, Unit] = recipientAdapter.save(recipient)
}
