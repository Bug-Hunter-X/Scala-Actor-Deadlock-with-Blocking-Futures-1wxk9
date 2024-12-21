```scala
import scala.concurrent.{ExecutionContext, Future}

class MyActor extends Actor {
  implicit val ec: ExecutionContext = ExecutionContext.global

  def receive: Receive = {
    case "start" =>
      val future1 = Future {
        Thread.sleep(5000)
        1
      }
      val future2 = Future {
        Thread.sleep(3000)
        2
      }
      // Use onComplete to avoid blocking the actor
      Future {
        val result = Future.sequence(Seq(future1, future2)).map(_.sum)
        result.onComplete {
          case scala.util.Success(value) => println(s"Sum: $value")
          case scala.util.Failure(exception) => println(s"Error: ${exception.getMessage}")
        }
      }
  }
}
```