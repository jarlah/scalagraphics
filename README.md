# Scala Graphics

This library makes it easier and more fun to write functional, lazy and composeable graphics operations for the Java Graphics api.

It can be used like this:

```scala
import com.github.jarlah.scalagraphics.GraphicsOp
import com.github.jarlah.scalagraphics.Java2DGraphicsIO
import com.github.jarlah.scalagraphics.GraphicsOp.*
import java.awt.Graphics

def renderHello: GraphicsOp[Unit] = for {
  _ <- clearRect(0, 0, 800, 600)
  _ <- setColor(Color.BLACK)
  _ <- drawString("Hello", 100, 100)
} yield ()

val g: Graphics = // somehow get graphics object
  renderHello.run(Java2DGraphicsIO(g))
```

or even

```scala
(getFont >>= setFont).run(Java2DGraphicsIO(graphics))
val font = new Font("Arial", 1, 12)

((pure(font) >>= setFont) >> drawString("Hello", 100, 100))
  .run(Java2DGraphicsIO(graphics))
```

To see how it can be used in an actual Java 2D game, see https://github.com/jarlah/scalagraphics-demo
