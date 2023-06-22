package com.github.jarlah.scalagraphics

case class Image(width: Int, height: Int, pixels: Array[Int])

object Image {
  def apply(width: Int, height: Int): Image = {
    Image(width, height, Array.fill(width * height)(0))
  }

  def apply(width: Int, height: Int, image: java.awt.Image): Image = {
    val pixels = new Array[Int](width * height)
    val bufferedImage = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB)
    val graphics = bufferedImage.createGraphics()
    graphics.drawImage(image, 0, 0, null)
    graphics.dispose()
    bufferedImage.getRGB(0, 0, width, height, pixels, 0, width)
    Image(width, height, pixels)
  }
}