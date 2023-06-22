package com.github.jarlah.scalagraphics

import cats.effect.IO
import cats.~>

trait GraphicsOpInterpreter {
  def interpret: GraphicsOp ~> Option

  def run[A](program: GraphicsIO[A]): Option[A] =
    program.foldMap(interpret)
}
