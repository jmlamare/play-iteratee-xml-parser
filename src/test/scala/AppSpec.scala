package jml.iteratees

import org.scalatest.{FunSuite, Matchers}
import play.api.libs.iteratee._
import scala.concurrent.duration.Duration
// import scala.concurrent.ExecutionContext.Implicits._
import play.api.libs.iteratee.Execution.Implicits._

class CheckSpec
  extends FunSuite
  with Matchers {

  test("xml comments extraction") {

    val results: List[String] = scala.concurrent.Await.result(
      IterateeUtils.extractComments(Enumerator.enumerate("abcd<!--efgh-->ijkl<!--mnop-->qrst".toCharArray)).run(
          Iteratee.getChunks
      ),
      Duration.Inf
    );

    results should contain theSameElementsAs List("efgh", "mnop", "")
  }

}
