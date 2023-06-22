package com.github.jarlah.scalagraphics

import cats.free.Free

import java.awt.Image
import java.awt.font.{FontRenderContext, GlyphVector}
import java.awt.geom.AffineTransform
import java.awt.image.renderable.RenderableImage
import java.awt.image.{BufferedImage, BufferedImageOp, ImageObserver, RenderedImage}
import java.text.AttributedCharacterIterator

sealed trait GraphicsOp[A]
case class ClearRect(x: Int, y: Int, width: Int, height: Int) extends GraphicsOp[Unit]
case class DrawImage(img: Image, x: Int, y: Int, width: Option[Int], height: Option[Int], color: Option[Color]) extends GraphicsOp[Boolean]
case class DrawLine(x1: Int, y1: Int, x2: Int, y2: Int) extends GraphicsOp[Unit]
case class DrawOval(x: Int, y: Int, width: Int, height: Int) extends GraphicsOp[Unit]
case class DrawRect(x: Int, y: Int, width: Int, height: Int) extends GraphicsOp[Unit]
case class DrawString(str: String, x: Int, y: Int) extends GraphicsOp[Unit]
case class FillRect(x: Int, y: Int, width: Int, height: Int) extends GraphicsOp[Unit]
case class FillOval(x: Int, y: Int, width: Int, height: Int) extends GraphicsOp[Unit]
case class GetColor() extends GraphicsOp[Option[Color]]
case class SetColor(color: Option[Color]) extends GraphicsOp[Unit]
case class GetFont() extends GraphicsOp[Option[Font]]
case class SetFont(font: Option[Font]) extends GraphicsOp[Unit]
