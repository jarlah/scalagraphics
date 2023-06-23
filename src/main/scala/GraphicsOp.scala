package com.github.jarlah.scalagraphics

import cats.free.Free

sealed trait GraphicsOp[A]
object GraphicsOp:
  case class Dimension(width: Int, height: Int)
  case class ClearRect(x: Int, y: Int, dimension: Dimension) extends GraphicsOp[Unit]
  case class DrawImage(img: Image, x: Int, y: Int, dimension: Option[Dimension], color: Option[Color]) extends GraphicsOp[Boolean]
  case class DrawLine(x1: Int, y1: Int, x2: Int, y2: Int) extends GraphicsOp[Unit]
  case class DrawOval(x: Int, y: Int, dimension: Dimension) extends GraphicsOp[Unit]
  case class DrawRect(x: Int, y: Int, dimension: Dimension) extends GraphicsOp[Unit]
  case class DrawString(str: String, x: Int, y: Int) extends GraphicsOp[Unit]
  case class FillRect(x: Int, y: Int, dimension: Dimension) extends GraphicsOp[Unit]
  case class FillOval(x: Int, y: Int, dimension: Dimension) extends GraphicsOp[Unit]
  case class GetColor() extends GraphicsOp[Option[Color]]
  case class SetColor(color: Option[Color]) extends GraphicsOp[Unit]
  case class GetFont() extends GraphicsOp[Option[Font]]
  case class SetFont(font: Option[Font]) extends GraphicsOp[Unit]
