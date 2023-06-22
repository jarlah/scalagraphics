package com.github.jarlah.scalagraphics

import com.github.jarlah.scalagraphics.{Color as GraphicsColor, Font as GraphicsFont}
import cats.*
import cats.implicits.*
import cats.effect.IO
import java.awt.*
import java.awt.font.{FontRenderContext, GlyphVector}
import java.awt.geom.AffineTransform
import java.awt.image.renderable.RenderableImage
import java.awt.image.{BufferedImage, BufferedImageOp, ImageObserver, RenderedImage}
import java.text.AttributedCharacterIterator
import scala.jdk.CollectionConverters.*

class Java2DGraphics(g: Graphics) extends GraphicsOpInterpreter{
  def interpret: GraphicsOp ~> IO = new(GraphicsOp ~> IO) {
    override def apply[A](fa: GraphicsOp[A]): IO[A] = fa match {
      case ClearRect(x, y, width, height) =>
        IO(g.clearRect(x, y, width, height))
      case DrawImage(img, x, y, Some(width), Some(height), Some(color)) =>
        IO(g.drawImage(img, x, y, width, height, new Color(color.r, color.g, color.b, color.a), null))
      case DrawImage(img, x, y, Some(width), Some(height), None) =>
        IO(g.drawImage(img, x, y, width, height, null))
      case DrawImage(img, x, y, _, _, Some(color)) =>
        IO(g.drawImage(img, x, y, new Color(color.r, color.g, color.b, color.a), null))
      case DrawImage(img, x, y, _, _, _) =>
        IO(g.drawImage(img, x, y, null))
      case DrawLine(x1, y1, x2, y2) =>
        IO(g.drawLine(x1, y1, x2, y2))
      case DrawRect(x, y, width, height) =>
        IO(g.drawRect(x, y, width, height))
      case DrawOval(x, y, width, height) =>
        IO(g.drawOval(x, y, width, height))
      case DrawString(str, x, y) =>
        IO(g.drawString(str, x, y))
      case FillOval(x, y, width, height) =>
        IO(g.fillOval(x, y, width, height))
      case FillRect(x, y, width, height) =>
        IO(g.fillRect(x, y, width, height))
      case SetColor(color) =>
        IO(color.foreach(color => g.setColor(new java.awt.Color(color.r, color.g, color.b, color.a))))
      case GetColor() =>
        IO(Option(g.getColor).map(c => GraphicsColor.rgba(c.getRed, c.getGreen, c.getBlue, c.getAlpha)))
      case SetFont(font) =>
        IO(font.foreach(font => g.setFont(new java.awt.Font(font.name, font.style.value, font.size))))
      case GetFont() =>
        IO(Option(g.getFont).map(f => GraphicsFont(f.getName, f.getSize, FontStyle.unsafeFromInt(f.getStyle))))
    }
  }
}