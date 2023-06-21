package com.github.jarlah.scalagraphics

import com.github.jarlah.scalagraphics.GraphicsOp.{
  drawString,
  getFont,
  pure,
  setFont
}
import org.mockito.ArgumentMatchers.{any, anyInt}
import org.mockito.Mockito.{spy, times, verify, when}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.jdk.CollectionConverters.*
import java.awt.{BasicStroke, Color, Font, Rectangle}

class GraphicsOpSpec extends AnyFunSuite {

  test("drawRect should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.drawRect(0, 0, 10, 10).run(GraphicsIOWrapper(graphics))
    verify(graphics).drawRect(0, 0, 10, 10)
  }

  test("drawImage should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    val image = mock[java.awt.image.BufferedImage]
    GraphicsOp.drawImage(image, 0, 0).run(GraphicsIOWrapper(graphics))
    verify(graphics).drawImage(image, 0, 0, null)
  }

  test("fillRect should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.fillRect(0, 0, 10, 10).run(GraphicsIOWrapper(graphics))
    verify(graphics).fillRect(0, 0, 10, 10)
  }

  test("setColor should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.setColor(Color.BLACK).run(GraphicsIOWrapper(graphics))
    verify(graphics).setColor(java.awt.Color.BLACK)
  }

  test("setStroke should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    val stroke = new BasicStroke(1)
    GraphicsOp.setStroke(stroke).run(GraphicsIOWrapper(graphics))
    verify(graphics).setStroke(stroke)
  }

  test("setFont should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .setFont(new java.awt.Font("Arial", 1, 12))
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).setFont(new java.awt.Font("Arial", 1, 12))
  }

  test("drawString should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.drawString("test", 0, 0).run(GraphicsIOWrapper(graphics))
    verify(graphics).drawString("test", 0, 0)
  }

  test("drawLine should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.drawLine(0, 0, 10, 10).run(GraphicsIOWrapper(graphics))
    verify(graphics).drawLine(0, 0, 10, 10)
  }

  test("drawPolygon should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .drawPolygon(Array(0, 0, 10, 10), Array(0, 10, 10, 0), 4)
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).drawPolygon(Array(0, 0, 10, 10), Array(0, 10, 10, 0), 4)
  }

  test("fillPolygon should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .fillPolygon(Array(0, 0, 10, 10), Array(0, 10, 10, 0), 4)
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).fillPolygon(Array(0, 0, 10, 10), Array(0, 10, 10, 0), 4)
  }

  test("drawOval should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.drawOval(0, 0, 10, 10).run(GraphicsIOWrapper(graphics))
    verify(graphics).drawOval(0, 0, 10, 10)
  }

  test("fillOval should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.fillOval(0, 0, 10, 10).run(GraphicsIOWrapper(graphics))
    verify(graphics).fillOval(0, 0, 10, 10)
  }

  test("drawArc should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.drawArc(0, 0, 10, 10, 0, 10).run(GraphicsIOWrapper(graphics))
    verify(graphics).drawArc(0, 0, 10, 10, 0, 10)
  }

  test("fillArc should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.fillArc(0, 0, 10, 10, 0, 10).run(GraphicsIOWrapper(graphics))
    verify(graphics).fillArc(0, 0, 10, 10, 0, 10)
  }

  test("drawRoundRect should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .drawRoundRect(0, 0, 10, 10, 0, 10)
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).drawRoundRect(0, 0, 10, 10, 0, 10)
  }

  test("fillRoundRect should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .fillRoundRect(0, 0, 10, 10, 0, 10)
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).fillRoundRect(0, 0, 10, 10, 0, 10)
  }

  test("translate should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.translate(10, 10).run(GraphicsIOWrapper(graphics))
    verify(graphics).translate(10.0, 10.0)
  }

  test("rotate should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.rotate(10).run(GraphicsIOWrapper(graphics))
    verify(graphics).rotate(10)
  }

  test("scale should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.scale(10, 10).run(GraphicsIOWrapper(graphics))
    verify(graphics).scale(10.0, 10.0)
  }

  test("shear should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.shear(10, 10).run(GraphicsIOWrapper(graphics))
    verify(graphics).shear(10.0, 10.0)
  }

  test("transform should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .transform(new java.awt.geom.AffineTransform())
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).transform(new java.awt.geom.AffineTransform())
  }

  test("clip should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .clip(new java.awt.Rectangle(0, 0, 10, 10))
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).clip(new java.awt.Rectangle(0, 0, 10, 10))
  }

  test("setClip should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .setClip(new java.awt.Rectangle(0, 0, 10, 10))
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).setClip(new java.awt.Rectangle(0, 0, 10, 10))
  }

  test("getClipBounds should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.getClipBounds.run(GraphicsIOWrapper(graphics))
    verify(graphics).getClipBounds
  }

  test("getClip should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.getClip.run(GraphicsIOWrapper(graphics))
    verify(graphics).getClip
  }

  test("setPaint should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .setPaint(new java.awt.Color(0, 0, 0))
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).setPaint(new java.awt.Color(0, 0, 0))
  }

  test("setComposite should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .setComposite(java.awt.AlphaComposite.SrcOver)
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).setComposite(java.awt.AlphaComposite.SrcOver)
  }

  test("setRenderingHint should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .setRenderingHint(
        java.awt.RenderingHints.KEY_ANTIALIASING,
        java.awt.RenderingHints.VALUE_ANTIALIAS_ON
      )
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).setRenderingHint(
      java.awt.RenderingHints.KEY_ANTIALIASING,
      java.awt.RenderingHints.VALUE_ANTIALIAS_ON
    )
  }

  test("getRenderingHint should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .getRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING)
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).getRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING)
  }

  test("setRenderingHints should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .setRenderingHints(
        Map(
          java.awt.RenderingHints.KEY_ANTIALIASING -> java.awt.RenderingHints.VALUE_ANTIALIAS_ON
        )
      )
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).setRenderingHints(
      Map(
        java.awt.RenderingHints.KEY_ANTIALIASING -> java.awt.RenderingHints.VALUE_ANTIALIAS_ON
      ).asJava
    )
    verify(graphics).setRenderingHints(
      new java.awt.RenderingHints(
        java.awt.RenderingHints.KEY_ANTIALIASING,
        java.awt.RenderingHints.VALUE_ANTIALIAS_ON
      )
    )
  }

  test("addRenderingHints should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .addRenderingHints(
        Map(
          java.awt.RenderingHints.KEY_ANTIALIASING -> java.awt.RenderingHints.VALUE_ANTIALIAS_ON
        )
      )
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).addRenderingHints(
      Map(
        java.awt.RenderingHints.KEY_ANTIALIASING -> java.awt.RenderingHints.VALUE_ANTIALIAS_ON
      ).asJava
    )
    verify(graphics).addRenderingHints(
      new java.awt.RenderingHints(
        java.awt.RenderingHints.KEY_ANTIALIASING,
        java.awt.RenderingHints.VALUE_ANTIALIAS_ON
      )
    )
  }

  test("getRenderingHints should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.getRenderingHints.run(GraphicsIOWrapper(graphics))
    verify(graphics).getRenderingHints
  }

  test("draw should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .draw(new java.awt.geom.Rectangle2D.Double(0, 0, 10, 10))
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).draw(new java.awt.geom.Rectangle2D.Double(0, 0, 10, 10))
  }

  test("fill should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp
      .fill(new java.awt.geom.Rectangle2D.Double(0, 0, 10, 10))
      .run(GraphicsIOWrapper(graphics))
    verify(graphics).fill(new java.awt.geom.Rectangle2D.Double(0, 0, 10, 10))
  }

  test("draw3DRect should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    when(graphics.getColor).thenReturn(java.awt.Color.BLUE)
    GraphicsOp.draw3DRect(0, 0, 10, 10, true).run(GraphicsIOWrapper(graphics))
    verify(graphics, times(1)).getColor
    verify(graphics, times(3)).setColor(any[java.awt.Color])
    verify(graphics, times(4)).drawLine(anyInt(), anyInt(), anyInt(), anyInt())
  }

  test("fill3DRect should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    when(graphics.getColor).thenReturn(java.awt.Color.BLUE)
    GraphicsOp.fill3DRect(0, 0, 10, 10, false).run(GraphicsIOWrapper(graphics))
    verify(graphics, times(1)).getColor
    verify(graphics, times(4)).setColor(any[java.awt.Color])
    verify(graphics, times(4)).drawLine(anyInt(), anyInt(), anyInt(), anyInt())
    verify(graphics, times(1)).fillRect(anyInt(), anyInt(), anyInt(), anyInt())
  }

  test("hitClip should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    val rectangle = spy(new Rectangle(0, 0, 10, 10))
    when(graphics.getClipBounds).thenReturn(rectangle)
    GraphicsOp.hitClip(0, 0, 10, 10).run(GraphicsIOWrapper(graphics))
    verify(rectangle).intersects(0, 0, 10, 10)
  }

  test("getFontMetrics should call the proper method in Graphics api") {
    val graphics = mock[java.awt.Graphics2D]
    val font = new java.awt.Font("Arial", 1, 12)
    GraphicsOp.getFontMetrics(font).run(GraphicsIOWrapper(graphics))
    verify(graphics).getFontMetrics(font)
  }

  test(
    "getFontMetrics should call the proper method in Graphics api when font is null"
  ) {
    val graphics = mock[java.awt.Graphics2D]
    GraphicsOp.getFontMetrics(null).run(GraphicsIOWrapper(graphics))
    verify(graphics).getFontMetrics(null)
  }

  test("composing two actions with the >> operator") {
    val graphics = mock[java.awt.Graphics2D]
    val action1 = GraphicsOp.setFont(new java.awt.Font("Arial", 1, 12))
    val action2 = GraphicsOp.setFont(new java.awt.Font("Arial", 1, 13))
    (action1 >> action2).run(GraphicsIOWrapper(graphics))
    verify(graphics, times(1)).setFont(new java.awt.Font("Arial", 1, 12))
    verify(graphics, times(1)).setFont(new java.awt.Font("Arial", 1, 13))
  }

  test("composing two actions with the >>= operator") {
    val graphics = mock[java.awt.Graphics2D]
    when(graphics.getFont).thenReturn(new java.awt.Font("Arial", 1, 12))
    val action1 = GraphicsOp.getFont
    val action2 = GraphicsOp.setFont
    (action1 >>= action2).run(GraphicsIOWrapper(graphics))
    verify(graphics, times(1)).getFont
    verify(graphics, times(1)).setFont(new java.awt.Font("Arial", 1, 12))

(getFont >>= setFont).run(GraphicsIOWrapper(graphics))
val font = new Font("Arial", 1, 12)
((pure(font) >>= setFont) >> drawString("Hello", 100, 100))
  .run(GraphicsIOWrapper(graphics))
  }
}
