package com.github.jarlah.scalagraphics

import java.awt.*
import java.awt.event.{ComponentAdapter, ComponentEvent, ComponentListener}
import java.awt.font.{FontRenderContext, GlyphVector}
import java.awt.geom.AffineTransform
import java.awt.image.renderable.RenderableImage
import java.awt.image.{BufferedImage, BufferedImageOp, ImageObserver, RenderedImage}
import java.text.AttributedCharacterIterator
import javax.swing.{ImageIcon, JFrame, WindowConstants}
import scala.annotation.{tailrec, targetName}
import scala.jdk.CollectionConverters.*
import scala.util.Try

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
    liftIO(_.drawImage(image, x, y, None))

  def drawImage(image: Image, x: Int, y: Int, color: GraphicsIO.Color): GraphicsOp[Boolean] =
    liftIO(_.drawImage(image, x, y, Some(color)))

  def drawImage(image: Image, x: Int, y: Int, width: Int, height: Int): GraphicsOp[Boolean] =
    liftIO(_.drawImage(image, x, y, width, height, None))

  def drawImage(image: Image, x: Int, y: Int, width: Int, height: Int, color: GraphicsIO.Color): GraphicsOp[Boolean] =
    liftIO(_.drawImage(image, x, y, width, height, Some(color)))

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

  def getColor: GraphicsOp[GraphicsIO.Color] =
    liftIO(_.getColor)

  def getFont: GraphicsOp[GraphicsIO.Font] =
    liftIO(_.getFont)

  def setFont(font: GraphicsIO.Font): GraphicsOp[Unit] =
    liftIO(_.setFont(font))

  def drawLine(x1: Int, y1: Int, x2: Int, y2: Int): GraphicsOp[Unit] =
    liftIO(_.drawLine(x1, y1, x2, y2))

  def fillRect(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
    liftIO(_.fillRect(x, y, width, height))

  def fillOval(x: Int, y: Int, width: Int, height: Int): GraphicsOp[Unit] =
    liftIO(_.fillOval(x, y, width, height))
}
