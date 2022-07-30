package arch.ddd

final case class Id[T <: Entity[_]](value: Long)
