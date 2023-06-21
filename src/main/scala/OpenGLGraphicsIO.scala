package com.github.jarlah.scalagraphics

import com.github.jarlah.scalagraphics.OpenGLGraphicsIO.OpenGLColor
import org.joml.Matrix4f

import java.awt.font.{FontRenderContext, GlyphVector}
import java.awt.geom.AffineTransform
import java.awt.image.renderable.RenderableImage
import java.awt.image.{BufferedImage, BufferedImageOp, ImageObserver, RenderedImage}
import java.awt.*
import java.text.AttributedCharacterIterator
import org.lwjgl.opengl.GL20.*
import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL15.*
import org.lwjgl.opengl.GL20.*
import org.lwjgl.opengl.GL30.*


class OpenGLGraphicsIO extends GraphicsIO {

  private var shaderProgram: Int = _
  private var colorUniform: Int = _
  private var transformUniform: Int = _
  private var vao: Int = _
  private var vbo: Int = _
  private var windowWidth: Int = _
  private var windowHeight: Int = _

  private var currentColor: GraphicsIO.Color = _

  def setupShaderProgram(): Unit = {
    val vertexShaderSource =
      """
        |#version 330 core
        |
        |layout (location = 0) in vec3 aPos;
        |uniform mat4 transform;
        |
        |void main()
        |{
        |    gl_Position = transform * vec4(aPos, 1.0);
        |}
        |""".stripMargin
    val fragmentShaderSource = "#version 330 core\n" +
      "out vec4 FragColor;\n" +
      "uniform vec4 color;\n" +
      "void main()\n" +
      "{\n" +
      "   FragColor = color;\n" +
      "}"

    val vertexShader = glCreateShader(GL_VERTEX_SHADER)
    glShaderSource(vertexShader, vertexShaderSource)
    glCompileShader(vertexShader)

    val fragmentShader = glCreateShader(GL_FRAGMENT_SHADER)
    glShaderSource(fragmentShader, fragmentShaderSource)
    glCompileShader(fragmentShader)

    shaderProgram = glCreateProgram()
    glAttachShader(shaderProgram, vertexShader)
    glAttachShader(shaderProgram, fragmentShader)
    glLinkProgram(shaderProgram)

    glDeleteShader(vertexShader)
    glDeleteShader(fragmentShader)

    colorUniform = glGetUniformLocation(shaderProgram, "color")
    transformUniform = glGetUniformLocation(shaderProgram, "transform")
  }

  def setupRectangle(): Unit = {
    vao = glGenVertexArrays()
    glBindVertexArray(vao)

    vbo = glGenBuffers()
    glBindBuffer(GL_ARRAY_BUFFER, vbo)

    val vertices = BufferUtils.createFloatBuffer(12)
    vertices.put(Array(
      0.0f, 0.0f, 0.0f,
      1.0f, 0.0f, 0.0f,
      1.0f, 1.0f, 0.0f,
      0.0f, 1.0f, 0.0f
    ))
    vertices.flip()

    glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW)
    glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)
    glEnableVertexAttribArray(0)

    glBindBuffer(GL_ARRAY_BUFFER, 0)
    glBindVertexArray(0)
  }

  def setWindowSize(width: Int, height: Int): Unit = {
    windowWidth = width
    windowHeight = height
  }

  override def drawImage(img: Image, x: Int, y: Int): Boolean = ???

  override def addRenderingHints(hints: Map[RenderingHints.Key, _]): Unit = ???

  override def clip(s: Shape): Unit = ???

  override def draw(s: Shape): Unit = ???

  override def drawGlyphVector(g: GlyphVector, x: Float, y: Float): Unit = ???

  override def drawImage(
      img: BufferedImage,
      op: BufferedImageOp,
      x: Int,
      y: Int
  ): Unit = ???

  override def drawImage(
      img: Image,
      xform: AffineTransform,
      obs: ImageObserver
  ): Boolean = ???

  override def drawRenderableImage(
      img: RenderableImage,
      xform: AffineTransform
  ): Unit = ???

  override def drawRenderedImage(
      img: RenderedImage,
      xform: AffineTransform
  ): Unit = ???

  override def fill(s: Shape): Unit = ???

  override def getBackground: Color = ???

  override def getComposite: Composite = ???

  override def getFontRenderContext: FontRenderContext = ???

  override def getPaint: Paint = ???

  override def getRenderingHint(hintKey: RenderingHints.Key): AnyRef = ???

  override def getRenderingHints: RenderingHints = ???

  override def getStroke: Stroke = ???

  override def getTransform: AffineTransform = ???

  override def hit(rect: Rectangle, s: Shape, onStroke: Boolean): Boolean = ???

  override def rotate(theta: Double): Unit = ???

  override def rotate(theta: Double, x: Double, y: Double): Unit = ???

  override def scale(sx: Double, sy: Double): Unit = ???

  override def setBackground(color: Color): Unit = ???

  override def setComposite(comp: AlphaComposite): Unit = ???

  override def setPaint(paint: Paint): Unit = ???

  override def setRenderingHint(
      hintKey: RenderingHints.Key,
      hintValue: Any
  ): Unit = ???

  override def setRenderingHints(hints: Map[_, _]): Unit = ???

  override def setStroke(s: Stroke): Unit = ???

  override def setTransform(Tx: AffineTransform): Unit = ???

  override def shear(shx: Double, shy: Double): Unit = ???

  override def transform(Tx: AffineTransform): Unit = ???

  override def translate(tx: Double, ty: Double): Unit = ???

  override def translate(x: Int, y: Int): Unit = ???

  override def getColor: GraphicsIO.Color =
    currentColor

  override def setColor(c: GraphicsIO.Color): Unit = {
    currentColor = c
    glUseProgram(shaderProgram)
    glUniform4f(colorUniform, c.r, c.g, c.b, c.a)
  }

  override def setPaintMode(): Unit = ???

  override def setXORMode(c1: Color): Unit = ???

  override def getFont: Font = ???

  override def setFont(font: Font): Unit = ???

  override def getFontMetrics(f: Font): FontMetrics = ???

  override def getClipBounds: Rectangle = ???

  override def clipRect(x: Int, y: Int, width: Int, height: Int): Unit = ???

  override def setClip(x: Int, y: Int, width: Int, height: Int): Unit = ???

  override def getClip: Shape = ???

  override def setClip(clip: Shape): Unit = ???

  override def copyArea(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      dx: Int,
      dy: Int
  ): Unit = ???

  override def drawLine(x1: Int, y1: Int, x2: Int, y2: Int): Unit = ???

  override def fillRect(x: Int, y: Int, width: Int, height: Int): Unit = ???

  override def clearRect(x: Int, y: Int, width: Int, height: Int): Unit = ???

  override def drawRoundRect(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      arcWidth: Int,
      arcHeight: Int
  ): Unit = ???

  override def fillRoundRect(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      arcWidth: Int,
      arcHeight: Int
  ): Unit = ???

  override def drawOval(x: Int, y: Int, width: Int, height: Int): Unit = ???

  override def fillOval(x: Int, y: Int, width: Int, height: Int): Unit = ???

  override def drawArc(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      startAngle: Int,
      arcAngle: Int
  ): Unit = ???

  override def fillArc(
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      startAngle: Int,
      arcAngle: Int
  ): Unit = ???

  override def drawPolyline(
      xPoints: Array[Int],
      yPoints: Array[Int],
      nPoints: Int
  ): Unit = ???

  override def drawPolygon(
      xPoints: Array[Int],
      yPoints: Array[Int],
      nPoints: Int
  ): Unit = ???

  override def fillPolygon(
      xPoints: Array[Int],
      yPoints: Array[Int],
      nPoints: Int
  ): Unit = ???

  override def drawString(str: String, x: Int, y: Int): Unit = ???

  override def drawString(
      iterator: AttributedCharacterIterator,
      x: Int,
      y: Int
  ): Unit = ???

  override def drawImage(
      img: Image,
      x: Int,
      y: Int,
      observer: ImageObserver
  ): Boolean = ???

  override def drawImage(
      img: Image,
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      observer: ImageObserver
  ): Boolean = ???

  override def drawImage(
      img: Image,
      x: Int,
      y: Int,
      bgcolor: Color,
      observer: ImageObserver
  ): Boolean = ???

  override def drawImage(
      img: Image,
      x: Int,
      y: Int,
      width: Int,
      height: Int,
      bgcolor: Color,
      observer: ImageObserver
  ): Boolean = ???

  override def drawImage(
      img: Image,
      dx1: Int,
      dy1: Int,
      dx2: Int,
      dy2: Int,
      sx1: Int,
      sy1: Int,
      sx2: Int,
      sy2: Int,
      observer: ImageObserver
  ): Boolean = ???

  override def drawImage(
      img: Image,
      dx1: Int,
      dy1: Int,
      dx2: Int,
      dy2: Int,
      sx1: Int,
      sy1: Int,
      sx2: Int,
      sy2: Int,
      bgcolor: Color,
      observer: ImageObserver
  ): Boolean = ???

  override def getFontMetrics: FontMetrics = ???

  override def drawPolygon(arg0: Polygon): Unit = ???

  override def fillPolygon(arg0: Polygon): Unit = ???

  override def getClipBounds(arg0: Rectangle): Rectangle = ???

  override def drawRect(x: Int, y: Int, width: Int, height: Int): Unit = {
    val projection = new Matrix4f().ortho2D(0, windowWidth, windowHeight, 0)
    val model = new Matrix4f().identity().translate(x, y, 0).scale(width, height, 1)
    val transform = new Matrix4f(projection).mul(model)
    val buffer = BufferUtils.createFloatBuffer(16)
    transform.get(buffer)

    glUseProgram(shaderProgram)
    glUniformMatrix4fv(transformUniform, false, buffer)
    glBindVertexArray(vao)
    glDrawArrays(GL_LINE_LOOP, 0, 4)
    glBindVertexArray(0)
  }

  override def draw3DRect(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Boolean
  ): Unit = ???

  override def fill3DRect(
      arg0: Int,
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Boolean
  ): Unit = ???

  override def drawChars(
      arg0: Array[Char],
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int
  ): Unit = ???

  override def drawBytes(
      arg0: Array[Byte],
      arg1: Int,
      arg2: Int,
      arg3: Int,
      arg4: Int
  ): Unit = ???

  override def getClipRect: Rectangle = ???

  override def hitClip(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Boolean =
    ???
}

object OpenGLGraphicsIO {
  case class OpenGLColor(r: Float, g: Float, b: Float, a: Float)
      extends GraphicsIO.Color
}
