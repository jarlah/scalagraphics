package com.github.jarlah.scalagraphics

import cats.effect.IO
import cats.~>

import scala.util.Try

trait GraphicsOpInterpreter {
  type EitherThrowable[A] = Either[Throwable, A]

  def interpret: GraphicsOp ~> EitherThrowable

  def run[A](program: GraphicsIO[A]): EitherThrowable[A] =
    program.foldMap(interpret)
}
