package com.github.jarlah.scalagraphics

import cats.free.Free

import java.awt.Image

type GraphicsIO[A] = Free[GraphicsOp, A]

def clearRect(x: Int, y: Int, width: Int, height: Int): GraphicsIO[Unit] =
  Free.liftF(ClearRect(x, y, width, height))
def drawImage(img: Image, x: Int, y: Int, width: Option[Int] = None, height: Option[Int] = None, color: Option[Color] = None): GraphicsIO[Boolean] =
  Free.liftF(DrawImage(img, x, y, width, height, color))
def getLine(x1: Int, y1: Int, x2: Int, y2: Int): GraphicsIO[Unit] =
  Free.liftF(DrawLine(x1, y1, x2, y2))
def drawOval(x: Int, y: Int, width: Int, height: Int): GraphicsIO[Unit] =
  Free.liftF(DrawOval(x, y, width, height))
def drawRect(x: Int, y: Int, width: Int, height: Int): GraphicsIO[Unit] =
  Free.liftF(DrawRect(x, y, width, height))
def drawString(str: String, x: Int, y: Int): GraphicsIO[Unit] =
  Free.liftF(DrawString(str, x, y))
def fillRect(x: Int, y: Int, width: Int, height: Int): GraphicsIO[Unit] =
  Free.liftF(FillRect(x, y, width, height))
def fillOval(x: Int, y: Int, width: Int, height: Int): GraphicsIO[Unit] =
  Free.liftF(FillOval(x, y, width, height))
def getColor: GraphicsIO[Color] =
  Free.liftF(GetColor())
def setColor(color: Color): GraphicsIO[Unit] =
  Free.liftF(SetColor(color))
def getFont: GraphicsIO[Font] =
  Free.liftF(GetFont())
def setFont(font: Font): GraphicsIO[Unit] =
  Free.liftF(SetFont(font))
def drawLine(x1: Int, y1: Int, x2: Int, y2: Int): GraphicsIO[Unit] =
  Free.liftF(DrawLine(x1, y1, x2, y2))
def pure[A](a: A): GraphicsIO[A] =
  Free.pure(a)