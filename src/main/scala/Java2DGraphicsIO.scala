package com.github.jarlah.scalagraphics

import java.awt.*
import java.awt.font.{FontRenderContext, GlyphVector}
import java.awt.geom.AffineTransform
import java.awt.image.renderable.RenderableImage
import java.awt.image.{
  BufferedImage,
  BufferedImageOp,
  ImageObserver,
  RenderedImage
}
import java.text.AttributedCharacterIterator
import scala.jdk.CollectionConverters.*

class Java2DGraphicsIO(g: Graphics2D) extends GraphicsIO {
  def this(graphics: Graphics) =
    this(graphics.asInstanceOf[Graphics2D])

  override def drawImage(img: Image, x: Int, y: Int): Boolean =
    g.drawImage(img, x, y, null)

  override def drawString(str: String, x: Int, y: Int): Unit =
    g.drawString(str, x, y)

  override def clearRect(x: Int, y: Int, width: Int, height: Int): Unit =
    g.clearRect(x, y, width, height)

  override def drawOval(x: Int, y: Int, width: Int, height: Int): Unit =
    g.drawOval(x, y, width, height)

  override def drawRect(x: Int, y: Int, width: Int, height: Int): Unit =
    g.drawRect(x, y, width, height)

  override def setColor(color: GraphicsIO.Color): Unit =
    g.setColor(new Color(color.r, color.g, color.b, color.a))

  override def translate(x: Int, y: Int): Unit =
    g.translate(x, y)

  override def getColor: Color =
    g.getColor

  override def setPaintMode(): Unit =
    g.setPaintMode()

  override def setXORMode(c1: Color): Unit =
    g.setXORMode(c1)

  override def getFont: Font =
    g.getFont

  override def setFont(font: Font): Unit =
    g.setFont(font)

  override def getFontMetrics(f: Font): FontMetrics =
    g.getFontMetrics(f)

  override def getClipBounds: Rectangle =
    g.getClipBounds

  override def clipRect(x: Int, y: Int, width: Int, height: Int): Unit =
    g.clipRect(x, y, width, height)

  override def setClip(x: Int, y: Int, width: Int, height: Int): Unit =
    g.setClip(x, y, width, height)

  override def getClip: Shape =
    g.getClip

  override def setClip(clip: Shape): Unit =
    g.setClip(clip)

  override def copyArea(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      dx: Int,
      dy: Int
  ): Unit =
    g.copyArea(x, y, width, height, dx, dy)

  override def drawLine(x1: Int, y1: Int, x2: Int, y2: Int): Unit =
    g.drawLine(x1, y1, x2, y2)

  override def fillRect(x: Int, y: Int, width: Int, height: Int): Unit =
    g.fillRect(x, y, width, height)

  override def drawRoundRect(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      arcWidth: Int,
      arcHeight: Int
  ): Unit =
    g.drawRoundRect(x, y, width, height, arcWidth, arcHeight)

  override def fillRoundRect(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      arcWidth: Int,
      arcHeight: Int
  ): Unit =
    g.fillRoundRect(x, y, width, height, arcWidth, arcHeight)

  override def fillOval(x: Int, y: Int, width: Int, height: Int): Unit =
    g.fillOval(x, y, width, height)

  override def drawArc(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      startAngle: Int,
      arcAngle: Int
  ): Unit =
    g.drawArc(x, y, width, height, startAngle, arcAngle)

  override def fillArc(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      startAngle: Int,
      arcAngle: Int
  ): Unit =
    g.fillArc(x, y, width, height, startAngle, arcAngle)

  override def drawPolyline(
      xPoints: Array[Int],
      yPoints: Array[Int],
      nPoints: Int
  ): Unit =
    g.drawPolyline(xPoints, yPoints, nPoints)

  override def drawPolygon(
      xPoints: Array[Int],
      yPoints: Array[Int],
      nPoints: Int
  ): Unit =
    g.drawPolygon(xPoints, yPoints, nPoints)

  override def fillPolygon(
      xPoints: Array[Int],
      yPoints: Array[Int],
      nPoints: Int
  ): Unit =
    g.fillPolygon(xPoints, yPoints, nPoints)

  override def drawString(
      iterator: AttributedCharacterIterator,
      x: Int,
      y: Int
  ): Unit =
    g.drawString(iterator, x, y)

  override def drawImage(
      img: Image,
      x: Int,
      y: Int,
      observer: ImageObserver
  ): Boolean =
    g.drawImage(img, x, y, observer)

  override def drawImage(
      img: Image,
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      observer: ImageObserver
  ): Boolean =
    g.drawImage(img, x, y, width, height, observer)

  override def drawImage(
      img: Image,
      x: Int,
      y: Int,
      bgcolor: Color,
      observer: ImageObserver
  ): Boolean =
    g.drawImage(img, x, y, bgcolor, observer)

  override def drawImage(
      img: Image,
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      bgcolor: Color,
      observer: ImageObserver
  ): Boolean =
    g.drawImage(img, x, y, width, height, bgcolor, observer)

  override def drawImage(
      img: Image,
      dx1: Int,
      dy1: Int,
      dx2: Int,
      dy2: Int,
      sx1: Int,
      sy1: Int,
      sx2: Int,
      sy2: Int,
      observer: ImageObserver
  ): Boolean =
    g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer)

  override def drawImage(
      img: Image,
      dx1: Int,
      dy1: Int,
      dx2: Int,
      dy2: Int,
      sx1: Int,
      sy1: Int,
      sx2: Int,
      sy2: Int,
      bgcolor: Color,
      observer: ImageObserver
  ): Boolean =
    g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer)

  override def addRenderingHints(
      hints: Map[java.awt.RenderingHints.Key, _]
  ): Unit =
    g.addRenderingHints(hints.asJava)

  override def clip(s: Shape): Unit =
    g.clip(s)

  override def draw(s: Shape): Unit =
    g.draw(s)

  override def drawGlyphVector(gv: GlyphVector, x: Float, y: Float): Unit =
    g.drawGlyphVector(gv, x, y)

  override def drawImage(
      img: BufferedImage,
      op: BufferedImageOp,
      x: Int,
      y: Int
  ): Unit =
    g.drawImage(img, op, x, y)

  override def drawImage(
      img: Image,
      xform: AffineTransform,
      obs: ImageObserver
  ): Boolean =
    g.drawImage(img, xform, obs)

  override def drawRenderableImage(
      img: RenderableImage,
      xform: AffineTransform
  ): Unit =
    g.drawRenderableImage(img, xform)

  override def drawRenderedImage(
      img: RenderedImage,
      xform: AffineTransform
  ): Unit =
    g.drawRenderedImage(img, xform)

  override def fill(s: Shape): Unit =
    g.fill(s)

  override def getBackground: Color =
    g.getBackground

  override def getComposite: Composite =
    g.getComposite

  override def getFontRenderContext: FontRenderContext =
    g.getFontRenderContext

  override def getPaint: Paint =
    g.getPaint

  override def getRenderingHint(hintKey: RenderingHints.Key): AnyRef =
    g.getRenderingHint(hintKey)

  override def getRenderingHints: RenderingHints =
    g.getRenderingHints

  override def getStroke: Stroke =
    g.getStroke

  override def getTransform: AffineTransform =
    g.getTransform

  override def hit(rect: Rectangle, s: Shape, onStroke: Boolean): Boolean =
    g.hit(rect, s, onStroke)

  override def rotate(theta: Double): Unit =
    g.rotate(theta)

  override def rotate(theta: Double, x: Double, y: Double): Unit =
    g.rotate(theta, x, y)

  override def scale(sx: Double, sy: Double): Unit =
    g.scale(sx, sy)

  override def setBackground(color: Color): Unit =
    g.setBackground(color)

  override def setComposite(comp: AlphaComposite): Unit =
    g.setComposite(comp)

  override def setPaint(paint: Paint): Unit =
    g.setPaint(paint)

  override def setRenderingHint(
      hintKey: RenderingHints.Key,
      hintValue: Any
  ): Unit =
    g.setRenderingHint(hintKey, hintValue)

  override def setRenderingHints(hints: Map[_, _]): Unit =
    g.setRenderingHints(hints.asJava)

  override def setStroke(s: Stroke): Unit =
    g.setStroke(s)

  override def setTransform(Tx: AffineTransform): Unit =
    g.setTransform(Tx)

  override def shear(shx: Double, shy: Double): Unit =
    g.shear(shx, shy)

  override def transform(Tx: AffineTransform): Unit =
    g.transform(Tx)

  override def translate(tx: Double, ty: Double): Unit =
    g.translate(tx, ty)

  override def getFontMetrics: FontMetrics =
    g.getFontMetrics

  override def drawPolygon(arg0: Polygon): Unit =
    g.drawPolygon(arg0)

  override def fillPolygon(arg0: Polygon): Unit =
    g.fillPolygon(arg0)

  override def getClipBounds(arg0: Rectangle): Rectangle =
    g.getClipBounds(arg0)

  override def draw3DRect(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Boolean
  ): Unit = g.draw3DRect(arg0, arg1, arg2, arg3, arg4)

  override def fill3DRect(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Boolean
  ): Unit = g.fill3DRect(arg0, arg1, arg2, arg3, arg4)

  override def drawChars(
      arg0: Array[Char],
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int
  ): Unit = g.drawChars(arg0, arg1, arg2, arg3, arg4)

  override def drawBytes(
      arg0: Array[Byte],
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int
  ): Unit = g.drawBytes(arg0, arg1, arg2, arg3, arg4)

  override def getClipRect: Rectangle = g.getClipRect

  override def hitClip(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Boolean =
    g.hitClip(arg0, arg1, arg2, arg3)
}
