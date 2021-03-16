import scala.quoted.*

object TypeInfo {
  inline def apply[T[_]]: String = ${ typeInfoImpl[T] }

  def typeInfoImpl[T[_]](using qctx: Quotes, tpe: Type[T]): Expr[String] = {
    import qctx.reflect.*

    val tpe = TypeRepr.of[T]
    
    val name = tpe.typeSymbol.name

    def fullTypeName(tpe: TypeRepr): String = tpe match
      case t: TypeRef => t.name
      case AppliedType(base, args) =>
        fullTypeName(base) + args.map(fullTypeName).mkString("[", ",", "]")

    val caseFields = tpe.typeSymbol.caseFields.map { s =>
      val name = s.name
      val tpe = s.tree match {
        case v: ValDef =>
          println(v.tpt.tpe)
          fullTypeName(v.tpt.tpe)
      }
      s"$name: $tpe"
    }

    Expr(
      s"$name(${caseFields.mkString(",")})"
    )
  }
}