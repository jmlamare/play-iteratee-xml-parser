package jml.iteratees

import org.scalatest.{FunSuite, Matchers}
import play.api.libs.iteratee._

//import scala.concurrent.ExecutionContext.Implicits._
//import play.api.libs.iteratee.Execution.Implicits._

import scala.concurrent.duration._
import scala.language.postfixOps

class CheckSpec
  extends FunSuite
  with Matchers {


  implicit val defaultExecutionContext: scala.concurrent.ExecutionContextExecutor = new scala.concurrent.ExecutionContextExecutor () {

    def execute(runnable: Runnable): Unit = runnable.run()

    def reportFailure(@deprecatedName('t) cause: Throwable): Unit = ???

  }


  test("xml comments extraction") {

    val results: List[String] = scala.concurrent.Await.result(
      IterateeUtils.extractComments(Enumerator.enumerate("abcd<!--efgh-->ijkl<!--mnop-->qrst".toCharArray)).run(
          Iteratee.getChunks
      ),
      10 seconds
    );

    results should contain theSameElementsAs List("efgh", "mnop")
  }

}
