package com.github.jarlah.scalagraphics

import java.awt.event.{ComponentAdapter, ComponentEvent, ComponentListener}
import java.awt.font.{FontRenderContext, GlyphVector}
import java.awt.geom.AffineTransform
import java.awt.image.renderable.RenderableImage
import java.awt.image.{
  BufferedImage,
  BufferedImageOp,
  ImageObserver,
  RenderedImage
}
import java.awt.{
  AlphaComposite,
  Color,
  Composite,
  Dimension,
  Font,
  FontMetrics,
  Graphics,
  Graphics2D,
  Image,
  Paint,
  Rectangle,
  RenderingHints,
  Shape,
  Stroke
}
import java.text.AttributedCharacterIterator
import javax.swing.{ImageIcon, JFrame, WindowConstants}
import scala.annotation.{tailrec, targetName}
import scala.util.Try
import scala.jdk.CollectionConverters.*

case class GraphicsOp[A](run: GraphicsIO => Either[Throwable, A]) {
  def map[B](f: A => B): GraphicsOp[B] =
    GraphicsOp(g => run(g).map(f))

  def flatMap[B](f: A => GraphicsOp[B]): GraphicsOp[B] =
    GraphicsOp(g => run(g).flatMap(a => f(a).run(g)))

  @targetName("compose")
  def >>[B](next: GraphicsOp[B]): GraphicsOp[B] =
    flatMap(_ => next)

  @targetName("bind")
  def >>=[B](f: A => GraphicsOp[B]): GraphicsOp[B] =
    flatMap(f)
}

object GraphicsOp {
  def pure[A](value: A): GraphicsOp[A] = GraphicsOp(g => Right(value))

  def liftIO[A](f: GraphicsIO => A): GraphicsOp[A] =
    GraphicsOp(g => Try(f(g)).toEither)

  def drawImage(image: Image, x: Int, y: Int): GraphicsOp[Boolean] =
    liftIO(_.drawImage(image, x, y))

  def drawString(str: String, x: Int, y: Int): GraphicsOp[Unit] =
    liftIO(_.drawString(str, x, y))

  def clearRect(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
    liftIO(_.clearRect(x, y, width, height))

  def drawOval(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
    liftIO(_.drawOval(x, y, width, height))

  def drawRect(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
    liftIO(_.drawRect(x, y, width, height))

  def setColor(color: GraphicsIO.Color): GraphicsOp[Unit] =
    liftIO(_.setColor(color))

  def translate(x: Double, y: Double): GraphicsOp[Unit] =
    liftIO(_.translate(x, y))

  def getColor: GraphicsOp[GraphicsIO.Color] =
    liftIO(_.getColor)

  def setPaintMode(): GraphicsOp[Unit] =
    liftIO(_.setPaintMode())

  def setXORMode(c1: Color): GraphicsOp[Unit] =
    liftIO(_.setXORMode(c1))

  def getFont: GraphicsOp[GraphicsIO.Font] =
    liftIO(_.getFont)

  def setFont(font: GraphicsIO.Font): GraphicsOp[Unit] =
    liftIO(_.setFont(font))

  def getFontMetrics(f: Font): GraphicsOp[FontMetrics] =
    liftIO(_.getFontMetrics(f))

  def getClipBounds: GraphicsOp[Rectangle] =
    liftIO(_.getClipBounds)

  def clipRect(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
    liftIO(_.clipRect(x, y, width, height))

  def setClip(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
    liftIO(_.setClip(x, y, width, height))

  def getClip: GraphicsOp[Shape] =
    liftIO(_.getClip)

  def setClip(clip: Shape): GraphicsOp[Unit] =
    liftIO(_.setClip(clip))

  def copyArea(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      dx: Int,
      dy: Int
  ): GraphicsOp[Unit] =
    liftIO(_.copyArea(x, y, width, height, dx, dy))

  def drawLine(x1: Int, y1: Int, x2: Int, y2: Int): GraphicsOp[Unit] =
    liftIO(_.drawLine(x1, y1, x2, y2))

  def fillRect(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
    liftIO(_.fillRect(x, y, width, height))

  def drawRoundRect(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      arcWidth: Int,
      arcHeight: Int
  ): GraphicsOp[Unit] =
    liftIO(
      _.drawRoundRect(x, y, width, height, arcWidth, arcHeight)
    )

  def fillRoundRect(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      arcWidth: Int,
      arcHeight: Int
  ): GraphicsOp[Unit] =
    liftIO(
      _.fillRoundRect(x, y, width, height, arcWidth, arcHeight)
    )

  def fillOval(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
    liftIO(_.fillOval(x, y, width, height))

  def drawArc(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      startAngle: Int,
      arcAngle: Int
  ): GraphicsOp[Unit] =
    liftIO(_.drawArc(x, y, width, height, startAngle, arcAngle))

  def fillArc(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      startAngle: Int,
      arcAngle: Int
  ): GraphicsOp[Unit] =
    liftIO(_.fillArc(x, y, width, height, startAngle, arcAngle))

  def drawPolyline(
      xPoints: Array[Int],
      yPoints: Array[Int],
      nPoints: Int
  ): GraphicsOp[Unit] =
    liftIO(_.drawPolyline(xPoints, yPoints, nPoints))

  def drawPolygon(
      xPoints: Array[Int],
      yPoints: Array[Int],
      nPoints: Int
  ): GraphicsOp[Unit] =
    liftIO(_.drawPolygon(xPoints, yPoints, nPoints))

  def fillPolygon(
      xPoints: Array[Int],
      yPoints: Array[Int],
      nPoints: Int
  ): GraphicsOp[Unit] =
    liftIO(_.fillPolygon(xPoints, yPoints, nPoints))

  def drawString(
      iterator: AttributedCharacterIterator,
      x: Int,
      y: Int
  ): GraphicsOp[Unit] =
    liftIO(_.drawString(iterator, x, y))

  def drawImage(
      img: Image,
      x: Int,
      y: Int,
      observer: ImageObserver
  ): GraphicsOp[Boolean] =
    liftIO(_.drawImage(img, x, y, observer))

  def drawImage(
      img: Image,
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      observer: ImageObserver
  ): GraphicsOp[Boolean] =
    liftIO(_.drawImage(img, x, y, width, height, observer))

  def drawImage(
      img: Image,
      x: Int,
      y: Int,
      bgcolor: Color,
      observer: ImageObserver
  ): GraphicsOp[Boolean] =
    liftIO(_.drawImage(img, x, y, bgcolor, observer))

  def drawImage(
      img: Image,
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      bgcolor: Color,
      observer: ImageObserver
  ): GraphicsOp[Boolean] =
    liftIO(
      _.drawImage(img, x, y, width, height, bgcolor, observer)
    )

  def drawImage(
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
  ): GraphicsOp[Boolean] =
    liftIO(
      _.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer)
    )

  def drawImage(
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
  ): GraphicsOp[Boolean] =
    liftIO(
      _.drawImage(
        img,
        dx1,
        dy1,
        dx2,
        dy2,
        sx1,
        sy1,
        sx2,
        sy2,
        bgcolor,
        observer
      )
    )

  def drawImage(
      img: Image,
      xform: AffineTransform,
      obs: ImageObserver
  ): GraphicsOp[Boolean] = liftIO(_.drawImage(img, xform, obs))

  def drawImage(
      img: BufferedImage,
      op: BufferedImageOp,
      x: Int,
      y: Int
  ): GraphicsOp[Unit] = liftIO(_.drawImage(img, op, x, y))

  def addRenderingHints(hints: Map[RenderingHints.Key, _]): GraphicsOp[Unit] =
    liftIO(
      _.addRenderingHints(hints)
    )

  def clip(s: Shape): GraphicsOp[Unit] = liftIO(_.clip(s))

  def draw(s: Shape): GraphicsOp[Unit] = liftIO(_.draw(s))

  def drawGlyphVector(g: GlyphVector, x: Float, y: Float): GraphicsOp[Unit] =
    liftIO(_.drawGlyphVector(g, x, y))

  def drawRenderableImage(
      img: RenderableImage,
      xform: AffineTransform
  ): GraphicsOp[Unit] = liftIO(_.drawRenderableImage(img, xform))

  def drawRenderedImage(
      img: RenderedImage,
      xform: AffineTransform
  ): GraphicsOp[Unit] = liftIO(_.drawRenderedImage(img, xform))

  def getBackground: GraphicsOp[Color] = liftIO(_.getBackground)

  def getComposite: GraphicsOp[Composite] = liftIO(_.getComposite)

  def getFontRenderContext: GraphicsOp[FontRenderContext] = liftIO(
    _.getFontRenderContext
  )

  def getPaint: GraphicsOp[Paint] = liftIO(_.getPaint)

  def getRenderingHint(hintKey: RenderingHints.Key): GraphicsOp[Object] =
    liftIO(
      _.getRenderingHint(hintKey)
    )

  def getRenderingHints: GraphicsOp[RenderingHints] = liftIO(
    _.getRenderingHints
  )

  def getStroke: GraphicsOp[Stroke] = liftIO(_.getStroke)

  def getTransform: GraphicsOp[AffineTransform] = liftIO(_.getTransform)

  def setBackground(color: Color): GraphicsOp[Unit] = liftIO(
    _.setBackground(color)
  )

  def setComposite(comp: AlphaComposite): GraphicsOp[Unit] = liftIO(
    _.setComposite(comp)
  )

  def setPaint(paint: Paint): GraphicsOp[Unit] = liftIO(_.setPaint(paint))

  def setRenderingHint(
      hintKey: RenderingHints.Key,
      hintValue: Object
  ): GraphicsOp[Unit] =
    liftIO(_.setRenderingHint(hintKey, hintValue))

  def setRenderingHints(hints: Map[_, _]): GraphicsOp[Unit] = liftIO(
    _.setRenderingHints(hints)
  )

  def setStroke(s: Stroke): GraphicsOp[Unit] = liftIO(_.setStroke(s))

  def setTransform(Tx: AffineTransform): GraphicsOp[Unit] = liftIO(
    _.setTransform(Tx)
  )

  def shear(shx: Double, shy: Double): GraphicsOp[Unit] = liftIO(
    _.shear(shx, shy)
  )

  def rotate(theta: Double): GraphicsOp[Unit] = liftIO(_.rotate(theta))

  def rotate(theta: Double, x: Double, y: Double): GraphicsOp[Unit] = liftIO(
    _.rotate(theta, x, y)
  )

  def hit(rect: Rectangle, s: Shape, onStroke: Boolean): GraphicsOp[Boolean] =
    liftIO(_.hit(rect, s, onStroke))

  def scale(sx: Double, sy: Double): GraphicsOp[Unit] = liftIO(
    _.scale(sx, sy)
  )

  def fill(s: Shape): GraphicsOp[Unit] = liftIO(_.fill(s))

  def transform(Tx: AffineTransform): GraphicsOp[Unit] = liftIO(
    _.transform(Tx)
  )

  def draw3DRect(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      raise: Boolean
  ): GraphicsOp[Unit] = liftIO(
    _.draw3DRect(x, y, width, height, raise)
  )

  def fill3DRect(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      raise: Boolean
  ): GraphicsOp[Unit] = liftIO(
    _.fill3DRect(x, y, width, height, raise)
  )

  def drawChars(
      data: Array[Char],
      offset: Int,
      length: Int,
      x: Int,
      y: Int
  ): GraphicsOp[Unit] = liftIO(
    _.drawChars(data, offset, length, x, y)
  )

  def drawBytes(
      data: Array[Byte],
      offset: Int,
      length: Int,
      x: Int,
      y: Int
  ): GraphicsOp[Unit] = liftIO(
    _.drawBytes(data, offset, length, x, y)
  )

  @deprecated
  def getClipRect: GraphicsOp[Rectangle] = liftIO(
    _.getClipRect
  )

  def hitClip(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Boolean] =
    liftIO(_.hitClip(x, y, width, height))
}
