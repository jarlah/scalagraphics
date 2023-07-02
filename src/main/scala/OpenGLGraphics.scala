package com.github.jarlah.scalagraphics

import cats.effect.IO
import cats.~>
import org.joml.Matrix4f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL15.*
import org.lwjgl.opengl.GL20.*
import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL
import cats.free.Free
import GraphicsOp.*
import Color.Black
import FontStyle.Plain

import org.lwjgl.nanovg.NanoVG.nvgCreateFont
import org.lwjgl.nanovg.NanoVGGL3.*
import org.lwjgl.opengl.GL13._

import scala.util.*

class OpenGLGraphics extends GraphicsOpInterpreter {

  private var shaderProgram: Int = _

  private var lineShaderProgram: Int = _

  private var colorUniform: Int = _
  private var transformUniform: Int = _
  private var useTextureUniform: Int = _

  private var vao: Int = _
  private var vbo: Int = _
  private var windowWidth: Int = _
  private var windowHeight: Int = _

  private var currentColor: Color = Black
  private var currentFont: Font = Font("Arial", 14, Plain)

  private var nanoVgPointer: Long = NULL

  // TODO: this should be setup by the application
  // instead lets set shaderProgram, colorUniform, transformUniform, vao, vbo etc with setters
  def setupShaderProgram(): Unit = {
    val vertexShaderSource =
      """
        |#version 330 core
        |
        |layout (location = 0) in vec3 aPos;
        |layout (location = 1) in vec2 aTexCoord;
        |
        |out vec2 TexCoord;
        |
        |uniform mat4 transform;
        |
        |void main()
        |{
        |    gl_Position = transform * vec4(aPos, 1.0);
        |    TexCoord = aTexCoord;
        |}
        |""".stripMargin

    val fragmentShaderSource =
      """
        |#version 330 core
        |
        |out vec4 FragColor;
        |
        |in vec2 TexCoord;
        |
        |uniform sampler2D texture1;
        |uniform vec4 color;
        |uniform bool useTexture;
        |
        |void main()
        |{
        |    if (useTexture) {
        |        FragColor = texture(texture1, TexCoord);
        |    } else {
        |        FragColor = color;
        |    }
        |}
        |""".stripMargin

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

    val lineVertexShaderSource =
      """
        |#version 330 core
        |
        |layout (location = 0) in vec3 aPos;
        |
        |uniform mat4 transform;
        |
        |void main()
        |{
        |    gl_Position = transform * vec4(aPos, 1.0);
        |}
        |""".stripMargin

    val lineFragmentShaderSource =
      """
        |#version 330 core
        |
        |out vec4 FragColor;
        |
        |uniform vec4 color;
        |
        |void main()
        |{
        |    FragColor = color;
        |}
        |""".stripMargin

    val lineVertexShader = glCreateShader(GL_VERTEX_SHADER)
    glShaderSource(lineVertexShader, lineVertexShaderSource)
    glCompileShader(lineVertexShader)

    val lineFragmentShader = glCreateShader(GL_FRAGMENT_SHADER)
    glShaderSource(lineFragmentShader, lineFragmentShaderSource)
    glCompileShader(lineFragmentShader)

    lineShaderProgram = glCreateProgram()
    glAttachShader(lineShaderProgram, lineVertexShader)
    glAttachShader(lineShaderProgram, lineFragmentShader)
    glLinkProgram(lineShaderProgram)

    glDeleteShader(lineVertexShader)
    glDeleteShader(lineFragmentShader)

    colorUniform = glGetUniformLocation(shaderProgram, "color")
    transformUniform = glGetUniformLocation(shaderProgram, "transform")
    useTextureUniform = glGetUniformLocation(shaderProgram, "useTexture")
  }

  // TODO: this should be setup by the application
  // instead lets set shaderProgram, colorUniform, transformUniform, vao, vbo etc with setters
  def setupRectangle(): Unit = {
    vao = glGenVertexArrays()
    glBindVertexArray(vao)

    vbo = glGenBuffers()
    glBindBuffer(GL_ARRAY_BUFFER, vbo)

    val vertices = BufferUtils.createFloatBuffer(20) // 5 floats per vertex: 3 for position, 2 for texture coordinates
    vertices.put(Array(
      0.0f, 0.0f, 0.0f, 0.0f, 0.0f, // Top-left corner
      1.0f, 0.0f, 0.0f, 1.0f, 0.0f, // Top-right corner
      1.0f, 1.0f, 0.0f, 1.0f, 1.0f, // Bottom-right corner
      0.0f, 1.0f, 0.0f, 0.0f, 1.0f  // Bottom-left corner
    ))
    vertices.flip()

    glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW)
    glVertexAttribPointer(0, 3, GL_FLOAT, false, 20, 0) // 20 = stride, the space between each set of 5 values
    glEnableVertexAttribArray(0)

    glVertexAttribPointer(1, 2, GL_FLOAT, false, 20, 12) // 12 = offset, the space from the start of a set of 5 values to the texture coordinates
    glEnableVertexAttribArray(1)

    glBindBuffer(GL_ARRAY_BUFFER, 0)
    glBindVertexArray(0)
  }

  def setupNanoVg(): Unit = {
    nanoVgPointer = nvgCreate(NVG_ANTIALIAS | NVG_STENCIL_STROKES)
    if (nanoVgPointer == NULL) {
      throw new RuntimeException("Could not init nanovg.")
    }

    val nanoVgFont = nvgCreateFont(nanoVgPointer, currentFont.name, s"fonts/${currentFont.name}.ttf")
    if (nanoVgFont == -1) {
      throw new RuntimeException("Could not add font.")
    }
  }

  def setWindowSize(width: Int, height: Int): Unit = {
    windowWidth = width
    windowHeight = height
  }

  def getWindowWidth: Int = windowWidth

  def getWindowHeight: Int = windowHeight

  private def getColor: Option[Color] =
    Option(currentColor)

  private def setColor(c: Option[Color]): Unit = {
    c.foreach(color => {
      currentColor = color
      glUseProgram(shaderProgram)
      glUniform4f(colorUniform, color.r, color.g, color.b, color.a)
    })
  }

  def setFont(font: Option[Font]): Unit = {
    currentFont = font.orNull
  }

  private def getFont = Option(currentFont)

  private def drawString(text: String, x: Int, y: Int): Unit = {
    import org.lwjgl.nanovg.NVGColor
    import org.lwjgl.nanovg.NanoVG.*
    import org.lwjgl.nanovg.NanoVGGL3.*
    import org.lwjgl.system.MemoryStack.*
    import org.lwjgl.system.MemoryUtil.*

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

  private def drawRect(x: Int, y: Int, width: Int, height: Int): Unit = {
    drawOrFillRect(x, y, width, height, GL_LINE_LOOP)
  }

  private def fillRect(x: Int, y: Int, width: Int, height: Int): Unit = {
    drawOrFillRect(x, y, width, height, GL_QUADS)
  }

  private def clearRect(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Unit = {
    glScissor(arg0, arg1, arg2, arg3)
    glEnable(GL_SCISSOR_TEST)
    glClear(GL_COLOR_BUFFER_BIT)
    glDisable(GL_SCISSOR_TEST)
  }

  private def drawOrFillRect(x: Int, y: Int, width: Int, height: Int, drawType: Int): Unit = {
    val projection = new Matrix4f().ortho2D(0, windowWidth, windowHeight, 0)
    val model = new Matrix4f().identity().translate(x, y, 0).scale(width, height, 1)
    val transform = new Matrix4f(projection).mul(model)
    val buffer = BufferUtils.createFloatBuffer(16)
    transform.get(buffer)

    glUseProgram(shaderProgram)
    glUniform1i(useTextureUniform, 0)
    glUniformMatrix4fv(transformUniform, false, buffer)
    glBindVertexArray(vao)
    glDrawArrays(drawType, 0, 4)
    glBindVertexArray(0)
  }

  private def drawImage(img: Image, x: Int, y: Int, width: Int, height: Int): Unit = {
    // Load the image into a texture
    val textureId = glGenTextures()
    glBindTexture(GL_TEXTURE_2D, textureId)

    // Set texture parameters
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT)
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT)
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)

    // Upload the image data to the texture
    val imgBuffer = img.pixels
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, img.width, img.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, imgBuffer)

    // Unbind the texture
    glBindTexture(GL_TEXTURE_2D, 0)

    // Set the color to white before rendering the texture
    glUseProgram(shaderProgram)
    glUniform4f(colorUniform, 1.0f, 1.0f, 1.0f, 1.0f)

    // Now draw a rectangle with the texture applied
    glBindVertexArray(vao)
    glBindTexture(GL_TEXTURE_2D, textureId)

    // Set the active texture and bind it
    glActiveTexture(GL_TEXTURE0)
    glBindTexture(GL_TEXTURE_2D, textureId)
    glUniform1i(glGetUniformLocation(shaderProgram, "texture1"), 0)

    glUniform1i(useTextureUniform, 1)  // in drawImage

    val projection = new Matrix4f().ortho2D(0, windowWidth, windowHeight, 0)
    val model = new Matrix4f().identity().translate(x, y, 0).scale(width, height, 1)
    val transform = new Matrix4f(projection).mul(model)
    val transformBuffer = BufferUtils.createFloatBuffer(16)
    transform.get(transformBuffer)

    glUseProgram(shaderProgram)
    glUniformMatrix4fv(transformUniform, false, transformBuffer)
    glDrawArrays(GL_QUADS, 0, 4)

    glBindTexture(GL_TEXTURE_2D, 0)
    glBindVertexArray(0)
  }

  private def drawLine(x1: Int, y1: Int, x2: Int, y2: Int): Unit = {
    val vaoLine = glGenVertexArrays()
    glBindVertexArray(vaoLine)

    val vboLine = glGenBuffers()
    glBindBuffer(GL_ARRAY_BUFFER, vboLine)

    val vertices = BufferUtils.createFloatBuffer(6) // 2 vertices, each with 3 floats (x, y, z)
    vertices.put(Array(
      x1.toFloat, y1.toFloat, 0.0f, // first vertex
      x2.toFloat, y2.toFloat, 0.0f  // second vertex
    ))
    vertices.flip()

    glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW)
    glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)
    glEnableVertexAttribArray(0)

    glBindBuffer(GL_ARRAY_BUFFER, 0)
    glBindVertexArray(0)

    // Draw the line
    glUseProgram(lineShaderProgram)
    glBindVertexArray(vaoLine)
    glDrawArrays(GL_LINES, 0, 2) // GL_LINES: each pair of vertices forms a line
    glBindVertexArray(0)

    // Clean up: delete the VBO and VAO
    glDeleteBuffers(vboLine)
    glDeleteVertexArrays(vaoLine)
  }

  def interpret: GraphicsOp ~> EitherThrowable = new (GraphicsOp ~> EitherThrowable) {
    override def apply[A](fa: GraphicsOp[A]): EitherThrowable[A] = fa match {
      case SetColor(c) => Try(setColor(c)).toEither
      case SetFont(f) => Try(setFont(f)).toEither
      case DrawString(text, x, y) => Try(drawString(text, x, y)).toEither
      case DrawRect(x, y, Dimension(width, height)) => Try(drawRect(x, y, width, height)).toEither
      case FillRect(x, y, Dimension(width, height)) => Try(fillRect(x, y, width, height)).toEither
      case ClearRect(x, y, Dimension(width, height)) => Try(clearRect(x, y, width, height)).toEither
      case GetColor() => Try(getColor).toEither
      case GetFont() => Try(getFont).toEither
      case DrawImage(img, x, y, Some(Dimension(width, height)), _) => Try(drawImage(img, x, y, width, height)).map(_ => true).toEither
      case DrawImage(img, x, y, None, _) => Try(drawImage(img, x, y, img.width, img.height)).map(_ => true).toEither
      case DrawLine(x1, y1, x2, y2) => Try(drawLine(x1, y1, x2, y2)).toEither
      case other => Left(new RuntimeException(s"Unsupported operation: $other"))
    }
  }
}