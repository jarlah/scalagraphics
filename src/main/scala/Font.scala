package com.github.jarlah.scalagraphics

trait FontStyle(val value: Int)
object FontStyle:
  case object Plain extends FontStyle(0)
  case object Bold extends FontStyle(1)
  case object Italic extends FontStyle(2)
  case object BoldItalic extends FontStyle(3)

  def unsafeFromInt(value: Int): FontStyle = value match {
    case 0 => Plain
    case 1 => Bold
    case 2 => Italic
    case 3 => BoldItalic
    case _ => throw new IllegalArgumentException("Invalid value for FontStyle: " + value)
  }

case class Font(name: String, size: Int, style: FontStyle) {
  def this(nameStr: String, sizeInt: Int, styleInt: Int) =
    this(nameStr, sizeInt, FontStyle.unsafeFromInt(styleInt))
}
