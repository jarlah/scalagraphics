package com.github.jarlah.scalagraphics

import java.awt.font.{FontRenderContext, GlyphVector}
import java.awt.geom.AffineTransform
import java.awt.image.renderable.RenderableImage
import java.awt.image.{
  BufferedImage,
  BufferedImageOp,
  ImageObserver,
  RenderedImage
}
import java.awt.*
import java.text.AttributedCharacterIterator

trait GraphicsIO {
  def drawImage(img: Image, x: Int, y: Int): Boolean = throw new IllegalStateException("drawImage is not implemented")

  def addRenderingHints(hints: Map[RenderingHints.Key, _]): Unit = throw new IllegalStateException("drawImage is not implemented")

  def clip(s: Shape): Unit = throw new IllegalStateException("clip is not implemented")

  def draw(s: Shape): Unit = throw new IllegalStateException("draw is not implemented")

  def drawGlyphVector(g: GlyphVector, x: Float, y: Float): Unit = throw new IllegalStateException("drawGlyphVector is not implemented")

  def drawImage(img: BufferedImage, op: BufferedImageOp, x: Int, y: Int): Unit = throw new IllegalStateException("drawImage is not implemented")

  def drawImage(img: Image, xform: AffineTransform, obs: ImageObserver): Boolean = throw new IllegalStateException("drawImage is not implemented")

  def drawRenderableImage(img: RenderableImage, xform: AffineTransform): Unit = throw new IllegalStateException("drawRenderableImage is not implemented")

  def drawRenderedImage(img: RenderedImage, xform: AffineTransform): Unit = throw new IllegalStateException("drawRenderedImage is not implemented")

  def fill(s: Shape): Unit = throw new IllegalStateException("fill is not implemented")

  def getBackground: Color  = throw new IllegalStateException("getBackground is not implemented")

  def getComposite: Composite  = throw new IllegalStateException("getComposite is not implemented")

  def getFontRenderContext: FontRenderContext  = throw new IllegalStateException("getFontRenderContext is not implemented")

  def getPaint: Paint  = throw new IllegalStateException("getPaint is not implemented")

  def getRenderingHint(hintKey: RenderingHints.Key): AnyRef  = throw new IllegalStateException("getRenderingHint is not implemented")

  def getRenderingHints: RenderingHints  = throw new IllegalStateException("getRenderingHints is not implemented")

  def getStroke: Stroke  = throw new IllegalStateException("getStroke is not implemented")

  def getTransform: AffineTransform  = throw new IllegalStateException("getTransform is not implemented")

  def hit(rect: Rectangle, s: Shape, onStroke: Boolean): Boolean = throw new IllegalStateException("hit is not implemented")

  def rotate(theta: Double): Unit = throw new IllegalStateException("rotate is not implemented")

  def rotate(theta: Double, x: Double, y: Double): Unit = throw new IllegalStateException("rotate is not implemented")

  def scale(sx: Double, sy: Double): Unit = throw new IllegalStateException("scale is not implemented")

  def setBackground(color: Color): Unit = throw new IllegalStateException("setBackground is not implemented")

  def setComposite(comp: AlphaComposite): Unit = throw new IllegalStateException("setComposite is not implemented")

  def setPaint(paint: Paint): Unit = throw new IllegalStateException("setPaint is not implemented")

  def setRenderingHint(hintKey: RenderingHints.Key, hintValue: Any): Unit = throw new IllegalStateException("setRenderingHint is not implemented")

  def setRenderingHints(hints: Map[_, _]): Unit = throw new IllegalStateException("setRenderingHints is not implemented")

  def setStroke(s: Stroke): Unit = throw new IllegalStateException("setStroke is not implemented")

  def setTransform(Tx: AffineTransform): Unit = throw new IllegalStateException("setTransform is not implemented")

  def shear(shx: Double, shy: Double): Unit = throw new IllegalStateException("shear is not implemented")

  def transform(Tx: AffineTransform): Unit = throw new IllegalStateException("transform is not implemented")

  def translate(tx: Double, ty: Double): Unit = throw new IllegalStateException("translate is not implemented")

  def setColor(arg0: GraphicsIO.Color): Unit = throw new IllegalStateException("setColor is not implemented")

  def translate(arg0: Int, arg1: Int): Unit = throw new IllegalStateException("translate is not implemented")

  def clipRect(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = throw new IllegalStateException("clipRect is not implemented")

  def getFont: GraphicsIO.Font  = throw new IllegalStateException("getFont is not implemented")

  def getFontMetrics(arg0: Font): FontMetrics  = throw new IllegalStateException("getFontMetrics is not implemented")

  def getFontMetrics: FontMetrics  = throw new IllegalStateException("getFontMetrics is not implemented")

  def drawLine(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = throw new IllegalStateException("drawLine is not implemented")

  def getColor: GraphicsIO.Color  = throw new IllegalStateException("getColor is not implemented")

  def fillRect(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = throw new IllegalStateException("fillRect is not implemented")

  def drawPolygon(arg0: Array[Int], arg1: Array[Int], arg2: Int): Unit = throw new IllegalStateException("drawPolygon is not implemented")

  def drawPolygon(arg0: Polygon): Unit = throw new IllegalStateException("drawPolygon is not implemented")

  def fillPolygon(arg0: Array[Int], arg1: Array[Int], arg2: Int): Unit = throw new IllegalStateException("fillPolygon is not implemented")

  def fillPolygon(arg0: Polygon): Unit = throw new IllegalStateException("fillPolygon is not implemented")

  def drawString(arg0: AttributedCharacterIterator, arg1: Int, arg2: Int): Unit = throw new IllegalStateException("drawString is not implemented")

  def drawString(arg0: String, arg1: Int, arg2: Int): Unit = throw new IllegalStateException("drawString is not implemented")

  def getClipBounds(arg0: Rectangle): Rectangle  = throw new IllegalStateException("getClipBounds is not implemented")

  def getClipBounds: Rectangle  = throw new IllegalStateException("getClipBounds is not implemented")

  def setPaintMode(): Unit = throw new IllegalStateException("setPaintMode is not implemented")

  def setXORMode(arg0: Color): Unit = throw new IllegalStateException("setXORMode is not implemented")

  def setFont(arg0: GraphicsIO.Font): Unit = throw new IllegalStateException("setFont is not implemented")

  def setClip(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = throw new IllegalStateException("setClip is not implemented")

  def setClip(arg0: Shape): Unit = throw new IllegalStateException("setClip is not implemented")

  def getClip: Shape  = throw new IllegalStateException("getClip is not implemented")

  def copyArea(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Int
  ): Unit = throw new IllegalStateException("copyArea is not implemented")

  def drawRect(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = throw new IllegalStateException("drawRect is not implemented")

  def clearRect(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = throw new IllegalStateException("clearRect is not implemented")

  def drawRoundRect(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Int
  ): Unit = throw new IllegalStateException("drawRoundRect is not implemented")

  def fillRoundRect(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Int
  ): Unit = throw new IllegalStateException("fillRoundRect is not implemented")

  def draw3DRect(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Boolean
  ): Unit = throw new IllegalStateException("draw3DRect is not implemented")

  def fill3DRect(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Boolean
  ): Unit = throw new IllegalStateException("fill3DRect is not implemented")

  def drawOval(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = throw new IllegalStateException("drawOval is not implemented")

  def fillOval(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = throw new IllegalStateException("fillOval is not implemented")

  def drawArc(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Int
  ): Unit = throw new IllegalStateException("drawArc is not implemented")

  def fillArc(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Int
  ): Unit = throw new IllegalStateException("fillArc is not implemented")

  def drawPolyline(arg0: Array[Int], arg1: Array[Int], arg2: Int): Unit = throw new IllegalStateException("drawPolyline is not implemented")

  def drawChars(
      arg0: Array[Char],
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int
  ): Unit = throw new IllegalStateException("drawChars is not implemented")

  def drawBytes(
      arg0: Array[Byte],
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int
  ): Unit = throw new IllegalStateException("drawBytes is not implemented")

  def drawImage(
      arg0: Image,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Color,
      arg6: ImageObserver
  ): Boolean = throw new IllegalStateException("drawImage is not implemented")

  def drawImage(
      arg0: Image,
      arg1: Int,
      arg2: Int,
      arg3: Color,
      arg4: ImageObserver
  ): Boolean = throw new IllegalStateException("drawImage is not implemented")

  def drawImage(arg0: Image, arg1: Int, arg2: Int, arg3: ImageObserver): Boolean = throw new IllegalStateException("drawImage is not implemented")

  def drawImage(
      arg0: Image,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: ImageObserver
  ): Boolean = throw new IllegalStateException("drawImage is not implemented")

  def drawImage(
      arg0: Image,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Int,
      arg6: Int,
      arg7: Int,
      arg8: Int,
      arg9: ImageObserver
  ): Boolean = throw new IllegalStateException("drawImage is not implemented")

  def drawImage(
      arg0: Image,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Int,
      arg6: Int,
      arg7: Int,
      arg8: Int,
      arg9: Color,
      arg10: ImageObserver
  ): Boolean = throw new IllegalStateException("drawImage is not implemented")

  def getClipRect: Rectangle  = throw new IllegalStateException("getClipRect is not implemented")

  def hitClip(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Boolean = throw new IllegalStateException("hitClip is not implemented")
}

object GraphicsIO {

  trait FontStyle(val value: Int)

  object FontStyle {
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

  trait Color {
    val r: Float
    val g: Float
    val b: Float
    val a: Float
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
