package com.github.jarlah.scalagraphics

import cats.effect.IO
import cats.~>

import scala.util.Try

trait GraphicsOpInterpreter {
  def interpret: GraphicsOp ~> Try

  def run[A](program: GraphicsIO[A]): A =
    program.foldMap(interpret).toEither match {
      case Left(e) => throw e
      case Right(a) => a
    }
}
