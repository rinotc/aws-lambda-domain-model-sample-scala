package sample.adapter.recipient

import arch.ddd.Id
import sample.domain.recipient.Recipient

trait IRecipientAdapter {

  def load(recipientId: Id[Recipient]): Option[Recipient]

  def save(recipient: Recipient): Either[String, Unit]
}
