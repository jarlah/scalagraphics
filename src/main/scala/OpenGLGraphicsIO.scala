package com.github.jarlah.scalagraphics

import GraphicsIO.FontStyle
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

  private var currentColor: GraphicsIO.Color = GraphicsIO.Color.Black
  private var currentFont: GraphicsIO.Font = GraphicsIO.Font("Arial", 14, FontStyle.Plain)

  private var nanoVgPointer: Long = -1

  // TODO: this should be setup by the application
  // instead lets set shaderProgram, colorUniform, transformUniform, vao, vbo etc with setters
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

  // TODO: this should be setup by the application
  // instead lets set shaderProgram, colorUniform, transformUniform, vao, vbo etc with setters
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

  def setNanoVgPointer(pointer: Long): Unit = {
    nanoVgPointer = pointer
  }

  def getWindowWidth: Int = windowWidth
  def getWindowHeight: Int = windowHeight

  override def getColor: GraphicsIO.Color =
    currentColor

  override def setColor(c: GraphicsIO.Color): Unit = {
    Option(c).foreach(color => {
        currentColor = color
        glUseProgram(shaderProgram)
        glUniform4f(colorUniform, color.r, color.g, color.b, color.a)
    })
  }

  override def setFont(font: GraphicsIO.Font): Unit = {
    currentFont = font
  }

  override def drawString(text: String, x: Int, y: Int): Unit = {
    import org.lwjgl.nanovg.NanoVG._
    import org.lwjgl.nanovg.NanoVGGL3._
    import org.lwjgl.system.MemoryStack._
    import org.lwjgl.system.MemoryUtil._
    import org.lwjgl.nanovg.NVGColor

    if (nanoVgPointer == -1) {
        throw new RuntimeException("NanoVG pointer not set.")
    }

    nvgBeginFrame(nanoVgPointer, windowWidth, windowHeight, 1)

    nvgFontSize(nanoVgPointer, currentFont.size.toFloat)
    nvgFontFace(nanoVgPointer, currentFont.name)
    nvgTextAlign(nanoVgPointer, NVG_ALIGN_LEFT | NVG_ALIGN_MIDDLE)

    val color = NVGColor.create()
    color.r(currentColor.r).g(currentColor.g).b(currentColor.b).a(currentColor.a)
    nvgFillColor(nanoVgPointer, color)
    nvgText(nanoVgPointer, x, y, text)

    nvgEndFrame(nanoVgPointer)
  }

  override def drawRect(x: Int, y: Int, width: Int, height: Int): Unit = {
    drawOrFillRect(x, y, width, height, GL_LINE_LOOP)
  }

  override def fillRect(x: Int, y: Int, width: Int, height: Int): Unit = {
    drawOrFillRect(x, y, width, height, GL_QUADS)
  }

  private def drawOrFillRect(x: Int, y: Int, width: Int, height: Int, drawType: Int): Unit = {
    val projection = new Matrix4f().ortho2D(0, windowWidth, windowHeight, 0)
    val model = new Matrix4f().identity().translate(x, y, 0).scale(width, height, 1)
    val transform = new Matrix4f(projection).mul(model)
    val buffer = BufferUtils.createFloatBuffer(16)
    transform.get(buffer)

    glUseProgram(shaderProgram)
    glUniformMatrix4fv(transformUniform, false, buffer)
    glBindVertexArray(vao)
    glDrawArrays(drawType, 0, 4)
    glBindVertexArray(0)
  }
}