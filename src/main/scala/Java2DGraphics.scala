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
import scala.util.Try

class Java2DGraphics(g: Graphics) extends GraphicsOpInterpreter{
  def interpret: GraphicsOp ~> Try = new(GraphicsOp ~> Try) {
    override def apply[A](fa: GraphicsOp[A]): Try[A] = fa match {
      case ClearRect(x, y, width, height) =>
        Try(g.clearRect(x, y, width, height))
      case DrawImage(img, x, y, Some(width), Some(height), Some(color)) =>
        Try(g.drawImage(img, x, y, width, height, new Color(color.r, color.g, color.b, color.a), null))
      case DrawImage(img, x, y, Some(width), Some(height), None) =>
        Try(g.drawImage(img, x, y, width, height, null))
      case DrawImage(img, x, y, _, _, Some(color)) =>
        Try(g.drawImage(img, x, y, new Color(color.r, color.g, color.b, color.a), null))
      case DrawImage(img, x, y, _, _, _) =>
        Try(g.drawImage(img, x, y, null))
      case DrawLine(x1, y1, x2, y2) =>
        Try(g.drawLine(x1, y1, x2, y2))
      case DrawRect(x, y, width, height) =>
        Try(g.drawRect(x, y, width, height))
      case DrawOval(x, y, width, height) =>
        Try(g.drawOval(x, y, width, height))
      case DrawString(str, x, y) =>
        Try(g.drawString(str, x, y))
      case FillOval(x, y, width, height) =>
        Try(g.fillOval(x, y, width, height))
      case FillRect(x, y, width, height) =>
        Try(g.fillRect(x, y, width, height))
      case SetColor(color) =>
        Try(color.foreach(color => g.setColor(new java.awt.Color(color.r, color.g, color.b, color.a))))
      case GetColor() =>
        Try(Option(g.getColor).map(c => GraphicsColor.rgba(c.getRed, c.getGreen, c.getBlue, c.getAlpha)))
      case SetFont(font) =>
        Try(font.foreach(font => g.setFont(new java.awt.Font(font.name, font.style.value, font.size))))
      case GetFont() =>
        Try(Option(g.getFont).map(f => GraphicsFont(f.getName, f.getSize, FontStyle.unsafeFromInt(f.getStyle))))
    }
  }
}