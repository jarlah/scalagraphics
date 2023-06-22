package com.github.jarlah.scalagraphics

import cats.effect.IO
import cats.~>

trait GraphicsOpInterpreter {
  import cats.effect.unsafe.implicits.global

  def interpret: GraphicsOp ~> IO

  def run[A](program: GraphicsIO[A]): A =
    program.foldMap(interpret).unsafeRunSync()
}
