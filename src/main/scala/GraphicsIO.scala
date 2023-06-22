package com.github.jarlah.scalagraphics

import java.awt.*
import java.awt.font.{FontRenderContext, GlyphVector}
import java.awt.geom.AffineTransform
import java.awt.image.renderable.RenderableImage
import java.awt.image.{BufferedImage, BufferedImageOp, ImageObserver, RenderedImage}
import java.text.AttributedCharacterIterator

trait GraphicsIO {
  def clearRect(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = throw new IllegalStateException("clearRect is not implemented")

  def drawImage(img: Image, x: Int, y: Int, width: Int, height: Int, color: Option[GraphicsIO.Color]): Boolean = throw new IllegalStateException("drawImage is not implemented")

  def drawImage(img: Image, x: Int, y: Int, color: Option[GraphicsIO.Color]): Boolean = throw new IllegalStateException("drawImage is not implemented")

  def drawLine(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = throw new IllegalStateException("drawLine is not implemented")

  def drawOval(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = throw new IllegalStateException("drawOval is not implemented")

  def drawRect(x: Int, y: Int, width: Int, height: Int): Unit = throw new IllegalStateException("drawRect is not implemented")

  def drawString(str: String, x: Int, y: Int): Unit = throw new IllegalStateException("drawString is not implemented")

  def fillOval(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = throw new IllegalStateException("fillOval is not implemented")

  def fillRect(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = throw new IllegalStateException("fillRect is not implemented")

  def getColor: GraphicsIO.Color  = throw new IllegalStateException("getColor is not implemented")

  def setColor(arg0: GraphicsIO.Color): Unit = throw new IllegalStateException("setColor is not implemented")

  def getFont: GraphicsIO.Font  = throw new IllegalStateException("getFont is not implemented")

  def setFont(arg0: GraphicsIO.Font): Unit = throw new IllegalStateException("setFont is not implemented")
}

object GraphicsIO {

  trait FontStyle(val value: Int)

  trait Font {
    val name: String
    val style: FontStyle
    val size: Int

    override def toString: String = {
      name + "-" + style + "-" + size
    }
  }

  trait Color {
    val r: Float
    val g: Float
    val b: Float
    val a: Float
  }

  object FontStyle {
    def unsafeFromInt(value: Int): FontStyle = value match {
      case 0 => Plain
      case 1 => Bold
      case 2 => Italic
      case 3 => BoldItalic
      case _ => throw new IllegalArgumentException("Invalid value for FontStyle: " + value)
    }

    case object Plain extends FontStyle(0)

    case object Bold extends FontStyle(1)

    case object Italic extends FontStyle(2)

    case object BoldItalic extends FontStyle(3)
  }

  object Font {
    def apply(nameStr: String, sizeInt: Int, styleInt: FontStyle): Font = new Font {
      override val name: String = nameStr
      override val size: Int = sizeInt
      override val style: FontStyle = styleInt
    }
  }

  object Color {
    def rgba(r: Float, g: Float, b: Float, a: Float): Color = new Color {
      val r: Float = r
      val g: Float = g
      val b: Float = b
      val a: Float = a
    }

    case object Black extends Color {
      val r = 0.0f
      val g = 0.0f
      val b = 0.0f
      val a: Float = 1.0f
    }

    case object White extends Color {
      val r = 1.0f
      val g = 1.0f
      val b = 1.0f
      val a: Float = 1.0f
    }

    case object Green extends Color {
      val r: Float = java.awt.Color.GREEN.getRed / 255.0f
      val g: Float = java.awt.Color.GREEN.getGreen / 255.0f
      val b: Float = java.awt.Color.GREEN.getBlue / 255.0f
      val a: Float = java.awt.Color.GREEN.getAlpha / 255.0f
    }

    case object Yellow extends Color {
      val r: Float = java.awt.Color.YELLOW.getRed / 255.0f
      val g: Float = java.awt.Color.YELLOW.getGreen / 255.0f
      val b: Float = java.awt.Color.YELLOW.getBlue / 255.0f
      val a: Float = java.awt.Color.YELLOW.getAlpha / 255.0f
    }

    case object Red extends Color {
      val r: Float = java.awt.Color.RED.getRed / 255.0f
      val g: Float = java.awt.Color.RED.getGreen / 255.0f
      val b: Float = java.awt.Color.RED.getBlue / 255.0f
      val a: Float = java.awt.Color.RED.getAlpha / 255.0f
    }

    case object DarkGray extends Color {
      val r: Float = java.awt.Color.DARK_GRAY.getRed / 255.0f
      val g: Float = java.awt.Color.DARK_GRAY.getGreen / 255.0f
      val b: Float = java.awt.Color.DARK_GRAY.getBlue / 255.0f
      val a: Float = java.awt.Color.DARK_GRAY.getAlpha / 255.0f
    }

    case object Gray extends Color {
      val r: Float = java.awt.Color.GRAY.getRed / 255.0f
      val g: Float = java.awt.Color.GRAY.getGreen / 255.0f
      val b: Float = java.awt.Color.GRAY.getBlue / 255.0f
      val a: Float = java.awt.Color.GRAY.getAlpha / 255.0f
    }

    case object Orange extends Color {
      val r: Float = java.awt.Color.ORANGE.getRed / 255.0f
      val g: Float = java.awt.Color.ORANGE.getGreen / 255.0f
      val b: Float = java.awt.Color.ORANGE.getBlue / 255.0f
      val a: Float = java.awt.Color.ORANGE.getAlpha / 255.0f
    }
  }


}
