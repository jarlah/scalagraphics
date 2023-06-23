package com.github.jarlah.scalagraphics


trait Color {
  val r: Float
  val g: Float
  val b: Float
  val a: Float
}

case object Black extends Color {
  val r = 0.0f
  val g = 0.0f
  val b = 0.0f
  val a: Float = 1.0f
}

case object White extends Color {
  val r = 1.0f
  val g = 1.0f
  val b = 1.0f
  val a: Float = 1.0f
}

case object Green extends Color {
  val r: Float = java.awt.Color.GREEN.getRed / 255.0f
  val g: Float = java.awt.Color.GREEN.getGreen / 255.0f
  val b: Float = java.awt.Color.GREEN.getBlue / 255.0f
  val a: Float = java.awt.Color.GREEN.getAlpha / 255.0f
}

case object Yellow extends Color {
  val r: Float = java.awt.Color.YELLOW.getRed / 255.0f
  val g: Float = java.awt.Color.YELLOW.getGreen / 255.0f
  val b: Float = java.awt.Color.YELLOW.getBlue / 255.0f
  val a: Float = java.awt.Color.YELLOW.getAlpha / 255.0f
}

case object Red extends Color {
  val r: Float = java.awt.Color.RED.getRed / 255.0f
  val g: Float = java.awt.Color.RED.getGreen / 255.0f
  val b: Float = java.awt.Color.RED.getBlue / 255.0f
  val a: Float = java.awt.Color.RED.getAlpha / 255.0f
}

case object DarkGray extends Color {
  val r: Float = java.awt.Color.DARK_GRAY.getRed / 255.0f
  val g: Float = java.awt.Color.DARK_GRAY.getGreen / 255.0f
  val b: Float = java.awt.Color.DARK_GRAY.getBlue / 255.0f
  val a: Float = java.awt.Color.DARK_GRAY.getAlpha / 255.0f
}

case object Gray extends Color {
  val r: Float = java.awt.Color.GRAY.getRed / 255.0f
  val g: Float = java.awt.Color.GRAY.getGreen / 255.0f
  val b: Float = java.awt.Color.GRAY.getBlue / 255.0f
  val a: Float = java.awt.Color.GRAY.getAlpha / 255.0f
}

case object Orange extends Color {
  val r: Float = java.awt.Color.ORANGE.getRed / 255.0f
  val g: Float = java.awt.Color.ORANGE.getGreen / 255.0f
  val b: Float = java.awt.Color.ORANGE.getBlue / 255.0f
  val a: Float = java.awt.Color.ORANGE.getAlpha / 255.0f
}

object Color {
  def rgba(r: Float, g: Float, b: Float, a: Float): Color = new Color {
    val r: Float = r
    val g: Float = g
    val b: Float = b
    val a: Float = a
  }
}

