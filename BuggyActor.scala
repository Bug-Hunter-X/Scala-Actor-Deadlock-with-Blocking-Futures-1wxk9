```scala
import scala.concurrent.{ExecutionContext, Future}

class MyActor extends Actor {
  implicit val ec: ExecutionContext = ExecutionContext.global

  def receive: Receive = {
    case "start" =>
      val future1 = Future {
        // Simulate a long-running task
        Thread.sleep(5000)
        1
      }
      val future2 = Future {
        // Simulate another long-running task
        Thread.sleep(3000)
        2
      }
      // This will cause a potential deadlock if future1 and future2 are not completed before the actor is stopped
      val result = Future.sequence(Seq(future1, future2)).map(_.sum)
      result.onComplete {
        case scala.util.Success(value) => println(s"Sum: $value")
        case scala.util.Failure(exception) => println(s"Error: ${exception.getMessage}")
      }
  }
}
```