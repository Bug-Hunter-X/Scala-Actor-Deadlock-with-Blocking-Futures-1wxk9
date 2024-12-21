This repository demonstrates a common deadlock scenario in Scala actors. The `BuggyActor.scala` file contains an actor that blocks indefinitely on the completion of a `Future`.  The `FixedActor.scala` demonstrates a solution using `onComplete` within a separate `Future` to handle the results asynchronously without blocking the actor's main receive loop.