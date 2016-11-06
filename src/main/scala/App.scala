package jml.iteratees

import play.api.libs.iteratee._
//import scala.concurrent.ExecutionContext.Implicits._
import play.api.libs.iteratee.Execution.Implicits._
// import scala.concurrent._
// import scala.concurrent.duration._

object IterateeUtils {



  def skipUntil(target: String, acum: String = ""): Iteratee[Char, Boolean] = Cont {
    case Input.Empty => skipUntil(target)
    case Input.EOF => Done(false)
    case Input.El(ch) if(target.charAt(acum.length)!=ch) => skipUntil(target)
    case Input.El(ch) if(acum.length<target.length-1) => skipUntil(target, acum + target.charAt(acum.length))
    case Input.El(ch) => Done(true)
  }

  def keepUntil(target: String, acum: String = "", pos: Int = 0): Iteratee[Char, String] = Cont {
    case Input.Empty => keepUntil(target, acum, pos)
    case Input.EOF => Done(acum)
    case Input.El(ch) if(ch==target.charAt(pos)) => if( pos+1<target.length ) keepUntil(target, acum, pos+1) else Done(acum)
    case Input.El(ch) => keepUntil(target, acum + target.substring(0, pos) + ch, 0)
  }

  def extractComments(xmlChars: Enumerator[Char]): Enumerator[String] = xmlChars.through(
    Enumeratee.grouped(for {
      b <- skipUntil("<!--")
      x <- if (b) keepUntil("-->") else Done[Char, String]("")
    } yield x)
  )

}

object HelloWorld extends App {

  IterateeUtils.extractComments(Enumerator.enumerate(scala.io.Source.stdin)).apply(
    Iteratee.foreach[String](s=>println("=>" +s))
  )

}