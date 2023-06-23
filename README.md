# Scala Graphics

This library makes it easier and more fun to write functional, lazy and composeable graphics operations for the Java Graphics api.

It can be used like this:

```scala
import com.github.jarlah.scalagraphics.GraphicsOp
import com.github.jarlah.scalagraphics.Java2DGraphics
import com.github.jarlah.scalagraphics.GraphicsOp.*

def renderHello: GraphicsOp[Unit] = for {
  _ <- clearRect(0, 0, 800, 600)
  _ <- setColor(Black)
  _ <- drawString("Hello", 100, 100)
} yield ()

val g: java.awt.Graphics = // somehow get graphics object
Java2DGraphics(g).run(renderHello) match {
  case Left(error) => println(s"Error while rendering: $error")
  case Right(_) =>
}
```

The library is using the Cats Free Monad to create a DSL for graphics operations. The operations are then interpreted to Java Graphics operations. The library also provides a way to interpret the operations to OpenGL operations.

To see how it can be used in an actual Java 2D game, see https://github.com/jarlah/scalagraphics-demo, or to see how it can be used in a OpenGL game, see https://github.com/jarlah/scalagraphics-demo-opengl.
