package com.github.jarlah.scalagraphics

import org.mockito.ArgumentMatchers.{any, anyInt}
import org.mockito.Mockito.{spy, times, verify, when}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar.mock
import org.scalatest.Assertions._
import scala.jdk.CollectionConverters.*
import java.awt.{BasicStroke, Color, Font, Rectangle}
import com.github.jarlah.scalagraphics.Black

class Java2DGraphicsSpec extends AnyFunSuite {

}
//
//class Java2DGraphicsOpSpec extends AnyFunSuite {
//
//  test("drawRect should call the proper method in Graphics api") {
//    val graphics = mock[java.awt.Graphics2D]
//    GraphicsOp.drawRect(0, 0, 10, 10).run(Java2DGraphicsIO(graphics))
//    verify(graphics).drawRect(0, 0, 10, 10)
//  }
//
//  test("drawImage should call the proper method in Graphics api") {
//    val graphics = mock[java.awt.Graphics2D]
//    val image = mock[java.awt.image.BufferedImage]
//    GraphicsOp.drawImage(image, 0, 0).run(Java2DGraphicsIO(graphics))
//    verify(graphics).drawImage(image, 0, 0, null, null)
//  }
//
//  test("fillRect should call the proper method in Graphics api") {
//    val graphics = mock[java.awt.Graphics2D]
//    GraphicsOp.fillRect(0, 0, 10, 10).run(Java2DGraphicsIO(graphics))
//    verify(graphics).fillRect(0, 0, 10, 10)
//  }
//
//  test("setColor should call the proper method in Graphics api") {
//    val graphics = mock[java.awt.Graphics2D]
//    val throwableOrUnit = GraphicsOp
//      .setColor(GraphicsIO.Color.Black)
//      .run(Java2DGraphicsIO(graphics))
//    println(throwableOrUnit)
//    assert(
//      throwableOrUnit
//        .isRight
//    )
//    verify(graphics).setColor(java.awt.Color.BLACK)
//  }
//
//  test("setFont should call the proper method in Graphics api") {
//    val graphics = mock[java.awt.Graphics2D]
//    GraphicsOp
//      .setFont(GraphicsIO.Font("Arial", 12, GraphicsIO.FontStyle.Bold))
//      .run(Java2DGraphicsIO(graphics))
//    verify(graphics).setFont(new java.awt.Font("Arial", 1, 12))
//  }
//
//  test("drawString should call the proper method in Graphics api") {
//    val graphics = mock[java.awt.Graphics2D]
//    GraphicsOp.drawString("test", 0, 0).run(Java2DGraphicsIO(graphics))
//    verify(graphics).drawString("test", 0, 0)
//  }
//
//  test("drawLine should call the proper method in Graphics api") {
//    val graphics = mock[java.awt.Graphics2D]
//    GraphicsOp.drawLine(0, 0, 10, 10).run(Java2DGraphicsIO(graphics))
//    verify(graphics).drawLine(0, 0, 10, 10)
//  }
//
//  test("drawOval should call the proper method in Graphics api") {
//    val graphics = mock[java.awt.Graphics2D]
//    GraphicsOp.drawOval(0, 0, 10, 10).run(Java2DGraphicsIO(graphics))
//    verify(graphics).drawOval(0, 0, 10, 10)
//  }
//
//  test("fillOval should call the proper method in Graphics api") {
//    val graphics = mock[java.awt.Graphics2D]
//    GraphicsOp.fillOval(0, 0, 10, 10).run(Java2DGraphicsIO(graphics))
//    verify(graphics).fillOval(0, 0, 10, 10)
//  }
//
//  test("composing two actions with the >> operator") {
//    val graphics = mock[java.awt.Graphics2D]
//    val action1 = GraphicsOp.setFont(GraphicsIO.Font("Arial", 12, GraphicsIO.FontStyle.Bold))
//    val action2 = GraphicsOp.setFont(GraphicsIO.Font("Arial", 13, GraphicsIO.FontStyle.Bold))
//    (action1 >> action2).run(Java2DGraphicsIO(graphics))
//    verify(graphics, times(1)).setFont(new java.awt.Font("Arial", 1, 12))
//    verify(graphics, times(1)).setFont(new java.awt.Font("Arial", 1, 13))
//  }
//
//  test("composing two actions with the >>= operator") {
//    val graphics = mock[java.awt.Graphics2D]
//    when(graphics.getFont).thenReturn(new java.awt.Font("Arial", 1, 12))
//    val action1 = GraphicsOp.getFont
//    val action2 = GraphicsOp.setFont
//    (action1 >>= action2).run(Java2DGraphicsIO(graphics))
//    verify(graphics, times(1)).getFont
//    verify(graphics, times(1)).setFont(new java.awt.Font("Arial", 1, 12))
//  }
//}
