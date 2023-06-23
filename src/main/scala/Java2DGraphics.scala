package com.github.jarlah.scalagraphics

import cats.*
import cats.implicits.*
import cats.effect.IO

import scala.jdk.CollectionConverters.*
import scala.util.Try
import GraphicsOp.*

class Java2DGraphics(g: java.awt.Graphics) extends GraphicsOpInterpreter{
  def interpret: GraphicsOp ~> EitherThrowable = new(GraphicsOp ~> EitherThrowable) {
    override def apply[A](fa: GraphicsOp[A]): EitherThrowable[A] = fa match {
      case ClearRect(x, y, Dimension(width, height)) =>
        Try(g.clearRect(x, y, width, height)).toEither
      case DrawImage(img, x, y, Some(Dimension(width, height)), Some(color)) =>
        Try(g.drawImage(img.toJavaAwtImage, x, y, width, height, new java.awt.Color(color.r, color.g, color.b, color.a), null)).toEither
      case DrawImage(img, x, y, Some(Dimension(width, height)), None) =>
        Try(g.drawImage(img.toJavaAwtImage, x, y, width, height, null)).toEither
      case DrawImage(img, x, y, _, Some(color)) =>
        Try(g.drawImage(img.toJavaAwtImage, x, y, new java.awt.Color(color.r, color.g, color.b, color.a), null)).toEither
      case DrawImage(img, x, y, _, _) =>
        Try(g.drawImage(img.toJavaAwtImage, x, y, null)).toEither
      case DrawLine(x1, y1, x2, y2) =>
        Try(g.drawLine(x1, y1, x2, y2)).toEither
      case DrawRect(x, y, Dimension(width, height)) =>
        Try(g.drawRect(x, y, width, height)).toEither
      case DrawOval(x, y, Dimension(width, height)) =>
        Try(g.drawOval(x, y, width, height)).toEither
      case DrawString(str, x, y) =>
        Try(g.drawString(str, x, y)).toEither
      case FillOval(x, y, Dimension(width, height)) =>
        Try(g.fillOval(x, y, width, height)).toEither
      case FillRect(x, y, Dimension(width, height)) =>
        Try(g.fillRect(x, y, width, height)).toEither
      case SetColor(color) =>
        Try(color.foreach(color => g.setColor(new java.awt.Color(color.r, color.g, color.b, color.a)))).toEither
      case GetColor() =>
        Try(Option(g.getColor).map(c => Color.rgba(c.getRed, c.getGreen, c.getBlue, c.getAlpha))).toEither
      case SetFont(font) =>
        Try(font.foreach(font => g.setFont(new java.awt.Font(font.name, font.style.value, font.size)))).toEither
      case GetFont() =>
        Try(Option(g.getFont).map(f => Font(f.getName, f.getSize, FontStyle.unsafeFromInt(f.getStyle)))).toEither
    }
  }
}