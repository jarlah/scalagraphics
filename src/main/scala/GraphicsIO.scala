package com.github.jarlah.scalagraphics

import java.awt.font.{FontRenderContext, GlyphVector}
import java.awt.geom.AffineTransform
import java.awt.image.renderable.RenderableImage
import java.awt.image.{
  BufferedImage,
  BufferedImageOp,
  ImageObserver,
  RenderedImage
}
import java.awt.*

trait GraphicsIO extends Graphics {
  def drawImage(img: Image, x: Int, y: Int): Boolean

  def addRenderingHints(hints: Map[RenderingHints.Key, _]): Unit

  def clip(s: Shape): Unit

  def draw(s: Shape): Unit

  def drawGlyphVector(g: GlyphVector, x: Float, y: Float): Unit

  def drawImage(img: BufferedImage, op: BufferedImageOp, x: Int, y: Int): Unit

  def drawImage(img: Image, xform: AffineTransform, obs: ImageObserver): Boolean

  def drawRenderableImage(img: RenderableImage, xform: AffineTransform): Unit

  def drawRenderedImage(img: RenderedImage, xform: AffineTransform): Unit

  def fill(s: Shape): Unit

  def getBackground: Color

  def getComposite: Composite

  def getFontRenderContext: FontRenderContext

  def getPaint: Paint

  def getRenderingHint(hintKey: RenderingHints.Key): AnyRef

  def getRenderingHints: RenderingHints

  def getStroke: Stroke

  def getTransform: AffineTransform

  def hit(rect: Rectangle, s: Shape, onStroke: Boolean): Boolean

  def rotate(theta: Double): Unit

  def rotate(theta: Double, x: Double, y: Double): Unit

  def scale(sx: Double, sy: Double): Unit

  def setBackground(color: Color): Unit

  def setComposite(comp: AlphaComposite): Unit

  def setPaint(paint: Paint): Unit

  def setRenderingHint(hintKey: RenderingHints.Key, hintValue: Any): Unit

  def setRenderingHints(hints: Map[_, _]): Unit

  def setStroke(s: Stroke): Unit

  def setTransform(Tx: AffineTransform): Unit

  def shear(shx: Double, shy: Double): Unit

  def transform(Tx: AffineTransform): Unit

  def translate(tx: Double, ty: Double): Unit
}
