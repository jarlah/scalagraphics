package com.github.jarlah.scalagraphics

trait FontStyle(val value: Int)
case object Plain extends FontStyle(0)
case object Bold extends FontStyle(1)
case object Italic extends FontStyle(2)
case object BoldItalic extends FontStyle(3)

object FontStyle {
  def unsafeFromInt(value: Int): FontStyle = value match {
    case 0 => Plain
    case 1 => Bold
    case 2 => Italic
    case 3 => BoldItalic
    case _ => throw new IllegalArgumentException("Invalid value for FontStyle: " + value)
  }
}

trait Font {
  val name: String
  val style: FontStyle
  val size: Int

  override def toString: String = {
    name + "-" + style + "-" + size
  }
}

object Font {
  def apply(nameStr: String, sizeInt: Int, styleInt: FontStyle): Font = new Font {
    override val name: String = nameStr
    override val size: Int = sizeInt
    override val style: FontStyle = styleInt
  }
}