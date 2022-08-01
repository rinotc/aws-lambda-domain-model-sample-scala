package arch.ddd

trait Entity[T <: Entity[_]] { self =>
  val id: Id[T]
}
