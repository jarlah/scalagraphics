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
  def drawImage(img: Image, x: Int, y: Int): Boolean

  def addRenderingHints(hints: Map[RenderingHints.Key, _]): Unit

  def clip(s: Shape): Unit

  def draw(s: Shape): Unit

  def drawGlyphVector(g: GlyphVector, x: Float, y: Float): Unit

  def drawImage(img: BufferedImage, op: BufferedImageOp, x: Int, y: Int): Unit

  def drawImage(img: Image, xform: AffineTransform, obs: ImageObserver): Boolean

  def drawRenderableImage(img: RenderableImage, xform: AffineTransform): Unit

  def drawRenderedImage(img: RenderedImage, xform: AffineTransform): Unit

  def fill(s: Shape): Unit

  def getBackground: Color

  def getComposite: Composite

  def getFontRenderContext: FontRenderContext

  def getPaint: Paint

  def getRenderingHint(hintKey: RenderingHints.Key): AnyRef

  def getRenderingHints: RenderingHints

  def getStroke: Stroke

  def getTransform: AffineTransform

  def hit(rect: Rectangle, s: Shape, onStroke: Boolean): Boolean

  def rotate(theta: Double): Unit

  def rotate(theta: Double, x: Double, y: Double): Unit

  def scale(sx: Double, sy: Double): Unit

  def setBackground(color: Color): Unit

  def setComposite(comp: AlphaComposite): Unit

  def setPaint(paint: Paint): Unit

  def setRenderingHint(hintKey: RenderingHints.Key, hintValue: Any): Unit

  def setRenderingHints(hints: Map[_, _]): Unit

  def setStroke(s: Stroke): Unit

  def setTransform(Tx: AffineTransform): Unit

  def shear(shx: Double, shy: Double): Unit

  def transform(Tx: AffineTransform): Unit

  def translate(tx: Double, ty: Double): Unit

  def setColor(arg0: GraphicsIO.Color): Unit

  def translate(arg0: Int, arg1: Int): Unit

  def clipRect(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit

  def getFont: Font

  def getFontMetrics(arg0: Font): FontMetrics

  def getFontMetrics: FontMetrics

  def drawLine(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit

  def getColor: Color

  def fillRect(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit

  def drawPolygon(arg0: Array[Int], arg1: Array[Int], arg2: Int): Unit

  def drawPolygon(arg0: Polygon): Unit

  def fillPolygon(arg0: Array[Int], arg1: Array[Int], arg2: Int): Unit

  def fillPolygon(arg0: Polygon): Unit

  def drawString(arg0: AttributedCharacterIterator, arg1: Int, arg2: Int): Unit

  def drawString(arg0: String, arg1: Int, arg2: Int): Unit

  def getClipBounds(arg0: Rectangle): Rectangle

  def getClipBounds: Rectangle

  def setPaintMode(): Unit

  def setXORMode(arg0: Color): Unit

  def setFont(arg0: Font): Unit

  def setClip(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit

  def setClip(arg0: Shape): Unit

  def getClip: Shape

  def copyArea(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Int
  ): Unit

  def drawRect(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit

  def clearRect(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit

  def drawRoundRect(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Int
  ): Unit

  def fillRoundRect(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Int
  ): Unit

  def draw3DRect(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Boolean
  ): Unit

  def fill3DRect(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Boolean
  ): Unit

  def drawOval(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit

  def fillOval(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit

  def drawArc(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Int
  ): Unit

  def fillArc(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Int
  ): Unit

  def drawPolyline(arg0: Array[Int], arg1: Array[Int], arg2: Int): Unit

  def drawChars(
      arg0: Array[Char],
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int
  ): Unit

  def drawBytes(
      arg0: Array[Byte],
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int
  ): Unit

  def drawImage(
      arg0: Image,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: Color,
      arg6: ImageObserver
  ): Boolean

  def drawImage(
      arg0: Image,
      arg1: Int,
      arg2: Int,
      arg3: Color,
      arg4: ImageObserver
  ): Boolean

  def drawImage(arg0: Image, arg1: Int, arg2: Int, arg3: ImageObserver): Boolean

  def drawImage(
      arg0: Image,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int,
      arg5: ImageObserver
  ): Boolean

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
  ): Boolean

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
  ): Boolean

  def getClipRect: Rectangle

  def hitClip(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Boolean
}

object GraphicsIO {
  trait Color {
    val r: Float
    val g: Float
    val b: Float
    val a: Float
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
    val r = 0.0f
    val g = 1.0f
    val b = 0.0f
    val a: Float = 1.0f
  }
}
