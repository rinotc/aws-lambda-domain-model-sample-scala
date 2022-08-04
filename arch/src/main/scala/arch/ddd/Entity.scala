package arch.ddd

trait Entity[T <: Entity[_]] { self =>

  /**
   * エンティティは識別子を持ち、識別子によって比較される
   */
  val id: Id[T]
}
