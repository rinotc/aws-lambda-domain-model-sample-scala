package arch.ddd

trait Entity[T <: Entity[_]] { self =>
  assert(self.isInstanceOf[T], "Tは自分型")
}
