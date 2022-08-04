package sample.port.slot

import arch.ddd.Id
import sample.domain.slot.Slot

trait ISlotOutputPort {

  def getSlotById(slotId: Id[Slot]): Option[Slot]
}
