package com.github.jarlah.scalagraphics

import java.awt.event.{ComponentAdapter, ComponentEvent, ComponentListener}
import java.awt.font.{FontRenderContext, GlyphVector}
import java.awt.geom.AffineTransform
import java.awt.image.renderable.RenderableImage
import java.awt.image.{BufferedImage, BufferedImageOp, ImageObserver, RenderedImage}
import java.awt.{AlphaComposite, Color, Composite, Dimension, Font, FontMetrics, Graphics, Graphics2D, Image, Paint, Rectangle, RenderingHints, Shape, Stroke}
import java.text.AttributedCharacterIterator
import javax.swing.{ImageIcon, JFrame, WindowConstants}
import scala.annotation.tailrec
import scala.util.Try
import scala.jdk.CollectionConverters.*

trait GraphicsIO extends Graphics {
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
}

object GraphicsIOWrapper {
  def unsafeApply(graphics: Graphics): GraphicsIOWrapper = {
    graphics match {
      case g: Graphics2D => GraphicsIOWrapper(g)
      case _ => throw new IllegalArgumentException(s"Graphics object is not Graphics2D")
    }
  }
}

class GraphicsIOWrapper(g: Graphics2D) extends GraphicsIO {
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

  override def setColor(color: Color): Unit =
    g.setColor(color)

  override def create(): Graphics =
    g.create()

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

  override def copyArea(x: Int, y: Int, width: Int, height: Int, dx: Int, dy: Int): Unit =
    g.copyArea(x, y, width, height, dx, dy)

  override def drawLine(x1: Int, y1: Int, x2: Int, y2: Int): Unit =
    g.drawLine(x1, y1, x2, y2)

  override def fillRect(x: Int, y: Int, width: Int, height: Int): Unit =
    g.fillRect(x, y, width, height)

  override def drawRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int): Unit =
    g.drawRoundRect(x, y, width, height, arcWidth, arcHeight)

  override def fillRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int): Unit =
    g.fillRoundRect(x, y, width, height, arcWidth, arcHeight)

  override def fillOval(x: Int, y: Int, width: Int, height: Int): Unit =
    g.fillOval(x, y, width, height)

  override def drawArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int): Unit =
    g.drawArc(x, y, width, height, startAngle, arcAngle)

  override def fillArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int): Unit =
    g.fillArc(x, y, width, height, startAngle, arcAngle)

  override def drawPolyline(xPoints: Array[Int], yPoints: Array[Int], nPoints: Int): Unit =
    g.drawPolyline(xPoints, yPoints, nPoints)

  override def drawPolygon(xPoints: Array[Int], yPoints: Array[Int], nPoints: Int): Unit =
    g.drawPolygon(xPoints, yPoints, nPoints)

  override def fillPolygon(xPoints: Array[Int], yPoints: Array[Int], nPoints: Int): Unit =
    g.fillPolygon(xPoints, yPoints, nPoints)

  override def drawString(iterator: AttributedCharacterIterator, x: Int, y: Int): Unit =
    g.drawString(iterator, x, y)

  override def drawImage(img: Image, x: Int, y: Int, observer: ImageObserver): Boolean =
    g.drawImage(img, x, y, observer)

  override def drawImage(img: Image, x: Int, y: Int, width: Int, height: Int, observer: ImageObserver): Boolean =
    g.drawImage(img, x, y, width, height, observer)

  override def drawImage(img: Image, x: Int, y: Int, bgcolor: Color, observer: ImageObserver): Boolean =
    g.drawImage(img, x, y, bgcolor, observer)

  override def drawImage(img: Image, x: Int, y: Int, width: Int, height: Int, bgcolor: Color, observer: ImageObserver): Boolean =
    g.drawImage(img, x, y, width, height, bgcolor, observer)

  override def drawImage(img: Image, dx1: Int, dy1: Int, dx2: Int, dy2: Int, sx1: Int, sy1: Int, sx2: Int, sy2: Int, observer: ImageObserver): Boolean =
    g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer)

  override def drawImage(img: Image, dx1: Int, dy1: Int, dx2: Int, dy2: Int, sx1: Int, sy1: Int, sx2: Int, sy2: Int, bgcolor: Color, observer: ImageObserver): Boolean =
    g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer)

  override def dispose(): Unit =
    g.dispose()

  override def addRenderingHints(hints: Map[java.awt.RenderingHints.Key, _]): Unit =
    g.addRenderingHints(hints.asJava)

  override def clip(s: Shape): Unit =
    g.clip(s)

  override def draw(s: Shape): Unit =
    g.draw(s)

  override def drawGlyphVector(gv: GlyphVector, x: Float, y: Float): Unit =
    g.drawGlyphVector(gv, x, y)

  override def drawImage(img: BufferedImage, op: BufferedImageOp, x: Int, y: Int): Unit =
    g.drawImage(img, op, x, y)

  override def drawImage(img: Image, xform: AffineTransform, obs: ImageObserver): Boolean =
    g.drawImage(img, xform, obs)

  override def drawRenderableImage(img: RenderableImage, xform: AffineTransform): Unit =
    g.drawRenderableImage(img, xform)

  override def drawRenderedImage(img: RenderedImage, xform: AffineTransform): Unit =
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

  override def setRenderingHint(hintKey: RenderingHints.Key, hintValue: Any): Unit =
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
}

case class GraphicsOp[A](run: GraphicsIO => Either[Throwable, A]) {
  def map[B](f: A => B): GraphicsOp[B] =
    GraphicsOp(g => run(g).map(f))

  def flatMap[B](f: A => GraphicsOp[B]): GraphicsOp[B] =
    GraphicsOp(g => run(g).flatMap(a => f(a).run(g)))
}

object GraphicsOp {
  def pure[A](value: A): GraphicsOp[A] = GraphicsOp(g => Right(value))
  def liftIO[A](f: GraphicsIO => A): GraphicsOp[A] =
    GraphicsOp(g => Try(f(g)).toEither)
}

def drawImage(image: Image, x: Int, y: Int): GraphicsOp[Boolean] =
  GraphicsOp.liftIO(_.drawImage(image, x, y))

def drawString(str: String, x: Int, y: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.drawString(str, x, y))

def clearRect(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.clearRect(x, y, width, height))

def drawOval(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.drawOval(x, y, width, height))

def drawRect(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.drawRect(x, y, width, height))

def setColor(color: Color): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.setColor(color))

def create(): GraphicsOp[Graphics] =
  GraphicsOp.liftIO(_.create())

def translate(x: Double, y: Double): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.translate(x, y))

def getColor: GraphicsOp[Color] =
  GraphicsOp.liftIO(_.getColor)

def setPaintMode(): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.setPaintMode())

def setXORMode(c1: Color): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.setXORMode(c1))

def getFont: GraphicsOp[Font] =
  GraphicsOp.liftIO(_.getFont)

def setFont(font: Font): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.setFont(font))

def getFontMetrics(f: Font): GraphicsOp[FontMetrics] =
  GraphicsOp.liftIO(_.getFontMetrics(f))

def getClipBounds: GraphicsOp[Rectangle] =
  GraphicsOp.liftIO(_.getClipBounds)

def clipRect(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.clipRect(x, y, width, height))

def setClip(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.setClip(x, y, width, height))

def getClip: GraphicsOp[Shape] =
  GraphicsOp.liftIO(_.getClip)

def setClip(clip: Shape): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.setClip(clip))

def copyArea(x: Int, y: Int, width: Int, height: Int, dx: Int, dy: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.copyArea(x, y, width, height, dx, dy))

def drawLine(x1: Int, y1: Int, x2: Int, y2: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.drawLine(x1, y1, x2, y2))

def fillRect(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.fillRect(x, y, width, height))

def drawRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.drawRoundRect(x, y, width, height, arcWidth, arcHeight))

def fillRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.fillRoundRect(x, y, width, height, arcWidth, arcHeight))

def fillOval(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.fillOval(x, y, width, height))

def drawArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.drawArc(x, y, width, height, startAngle, arcAngle))

def fillArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.fillArc(x, y, width, height, startAngle, arcAngle))

def drawPolyline(xPoints: Array[Int], yPoints: Array[Int], nPoints: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.drawPolyline(xPoints, yPoints, nPoints))

def drawPolygon(xPoints: Array[Int], yPoints: Array[Int], nPoints: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.drawPolygon(xPoints, yPoints, nPoints))

def fillPolygon(xPoints: Array[Int], yPoints: Array[Int], nPoints: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.fillPolygon(xPoints, yPoints, nPoints))

def drawString(iterator: AttributedCharacterIterator, x: Int, y: Int): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.drawString(iterator, x, y))

def drawImage(img: Image, x: Int, y: Int, observer: ImageObserver): GraphicsOp[Boolean] =
  GraphicsOp.liftIO(_.drawImage(img, x, y, observer))

def drawImage(img: Image, x: Int, y: Int, width: Int, height: Int, observer: ImageObserver): GraphicsOp[Boolean] =
  GraphicsOp.liftIO(_.drawImage(img, x, y, width, height, observer))

def drawImage(img: Image, x: Int, y: Int, bgcolor: Color, observer: ImageObserver): GraphicsOp[Boolean] =
  GraphicsOp.liftIO(_.drawImage(img, x, y, bgcolor, observer))

def drawImage(img: Image, x: Int, y: Int, width: Int, height: Int, bgcolor: Color, observer: ImageObserver): GraphicsOp[Boolean] =
  GraphicsOp.liftIO(_.drawImage(img, x, y, width, height, bgcolor, observer))

def drawImage(img: Image, dx1: Int, dy1: Int, dx2: Int, dy2: Int, sx1: Int, sy1: Int, sx2: Int, sy2: Int, observer: ImageObserver): GraphicsOp[Boolean] =
  GraphicsOp.liftIO(_.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer))

def drawImage(img: Image, dx1: Int, dy1: Int, dx2: Int, dy2: Int, sx1: Int, sy1: Int, sx2: Int, sy2: Int, bgcolor: Color, observer: ImageObserver): GraphicsOp[Boolean] =
  GraphicsOp.liftIO(_.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer))

def dispose(): GraphicsOp[Unit] =
  GraphicsOp.liftIO(_.dispose())