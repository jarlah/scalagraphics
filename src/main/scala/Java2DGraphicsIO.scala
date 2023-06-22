package com.github.jarlah.scalagraphics

import GraphicsIO.FontStyle

import java.awt.*
import java.awt.font.{FontRenderContext, GlyphVector}
import java.awt.geom.AffineTransform
import java.awt.image.renderable.RenderableImage
import java.awt.image.{BufferedImage, BufferedImageOp, ImageObserver, RenderedImage}
import java.text.AttributedCharacterIterator
import scala.jdk.CollectionConverters.*

class Java2DGraphicsIO(g: Graphics2D) extends GraphicsIO {
  def this(graphics: Graphics) =
    this(graphics.asInstanceOf[Graphics2D])

  override def clearRect(x: Int, y: Int, width: Int, height: Int): Unit =
    g.clearRect(x, y, width, height)

  override def drawImage(img: Image, x: Int, y: Int, c: Option[GraphicsIO.Color]): Boolean =
    g.drawImage(img, x, y, c.map(c => new Color(c.r, c.g, c.b, c.a)).orNull, null)

  override def drawImage(img: Image, x: Int, y: Int, width: Int, height: Int, c: Option[GraphicsIO.Color]): Boolean =
    g.drawImage(img, x, y, width, height, c.map(c => new Color(c.r, c.g, c.b, c.a)).orNull, null)

  override def drawLine(x1: Int, y1: Int, x2: Int, y2: Int): Unit =
    g.drawLine(x1, y1, x2, y2)

  override def drawOval(x: Int, y: Int, width: Int, height: Int): Unit =
    g.drawOval(x, y, width, height)

  override def drawRect(x: Int, y: Int, width: Int, height: Int): Unit =
    g.drawRect(x, y, width, height)

  override def drawString(str: String, x: Int, y: Int): Unit =
    g.drawString(str, x, y)

  override def fillOval(x: Int, y: Int, width: Int, height: Int): Unit =
    g.fillOval(x, y, width, height)

  override def fillRect(x: Int, y: Int, width: Int, height: Int): Unit =
    g.fillRect(x, y, width, height)

  override def getColor: GraphicsIO.Color =
    Option(g.getColor).map(color =>
      GraphicsIO.Color.rgba(color.getRed / 255f, color.getGreen / 255f, color.getBlue / 255f, color.getAlpha / 255f)
    ).orNull

  override def setColor(color: GraphicsIO.Color): Unit =
    Option(color).foreach(color => g.setColor(new Color(color.r, color.g, color.b, color.a)))

  override def getFont: GraphicsIO.Font =
    Option(g.getFont).map(font =>
      GraphicsIO.Font(font.getName, font.getSize, FontStyle.unsafeFromInt(font.getStyle))
    ).orNull

  override def setFont(font: GraphicsIO.Font): Unit =
    g.setFont(new Font(font.name, font.style.value, font.size))
}
