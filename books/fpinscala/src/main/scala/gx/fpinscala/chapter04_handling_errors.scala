package gx.fpinscala

/**
  * Created by 10051039 on 2016/8/29.
  */

//hide std library `Option` and `Either`, since we are writing our own in this chapter
import scala.{Either => _, Option => _, _}


object chapter04_handling_errors {

  /*
    EXERCISE 1: We'll explore when you'd use each of these next. But first, as an
    exercise, implement all of the above functions on Option. As you implement
    each function, try to think about what it means and in what situations you'd use it.
   */

  sealed trait Option[+A] {
    def map[B](f: A => B): Option[B] = this match {
      case None => None
      case Some(x) => Some(f(x))
    }

    def flatMap[B](f: A => Option[B]): Option[B] = this match {
      case None => None
      case Some(x) => f(x)

    }

    def getOrElse[B >: A](default: => B): B = this match {
      case None => default
      case Some(x) => x
    }

    def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
      case None => ob
      case _ => this
    }

    def filter(f: A => Boolean): Option[A] = this match {
      case Some(x) if f(x) => this
      case _ => None
    }

  }

  case class Some[+A](x: A) extends Option[A]

  case object None extends Option[Nothing]

  /*
    EXERCISE 2: Implement the variance function (if the mean is m, variance
    is the mean of math.pow(x - m, 2), see ) in terms of and definition mean
    flatMap.
   */
  def mean(xs: Seq[Double]): Option[Double] = xs match {
    case Nil => None
    case _ => Some(xs.sum / xs.length)
  }

  def variance(xs: Seq[Double]): Option[Double] = {
    import scala.math.pow
    mean(xs).flatMap(m => mean(xs.map(x => pow(x - m, 2))))
    // for (m <- mean(xs)) yield mean(xs.map(x => pow(x - m, 2))).getOrElse(0.0)
  }

  //---------- implementations of examples ---------------
  def lift[A, B](f: A => B): Option[A] => Option[B] = {
    (x: Option[A]) => x map f
  }

  import java.util.regex._

  def pattern(s: String): Option[Pattern] = {
    try {
      Some(Pattern.compile(s))
    } catch {
      case e: PatternSyntaxException => None
    }
  }

  //--------------------------------------------------------

  /*
    EXERCISE 3: bothMatch is an instance of a more general pattern. Write a
    generic function map2, that combines two Option values using a binary function.
    If either Option value is None, then the return value is too.
   */
  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
    for (x <- a; y <- b) yield f(x, y)
  }

  /*
    EXERCISE 4: Re-implement bothMatch above in terms of this new function,
    to the extent possible.
   */
  def bothMatch_2(pat1: String, pat2: String, s: String): Option[Boolean] = {
    map2(pattern(pat1), pattern(pat2))((a, b) => a.matcher(s).matches && b.matcher(s).matches())
  }

  /*
    EXERCISE 5: Write a function sequence , that combines a list of Options
    into one option containing a list of all the Some values in the original list. If the
    original list contains None even once, the result of the function should be None,
    otherwise the result should be with a list of all the values.
   */
  def sequence[A](a: List[Option[A]]): Option[List[A]] = {
    //    if (a.contains(None)) None
    //    else Some(a.map(x => x.get))
    a.foldRight[Option[List[A]]](Some(Nil))((x, y) => map2(x, y)(_ :: _))
  }

  /*
    EXERCISE 6: Implement this function. It is straightforward to do using map
    and , but try for a more efficient implementation that only looks at the sequence
    list once.
   */
  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = {
    a.foldRight[Option[List[B]]](Some(Nil)) { (x, y) => map2(f(x), y)(_ :: _) }
  }


  /*
    EXERCISE 7: Implement versions of map, flatMap, orElse, and map2 on
    Either that operate on the Right value.
   */

  trait Either[+E, +A] {
    def map[B](f: A => B): Either[E, B] = this match {
      case Right(a) => Right(f(a))
      case Left(a) => Left(a)
    }

    def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
      case Right(a) => f(a)
      case Left(a) => Left(a)
    }

    def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
      case Left(a) => b
      case _ => this
    }

    def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = {
      for (aa <- this; bb <- b) yield f(aa, bb)
    }

  }

  final case class Left[+E](value: E) extends Either[E, Nothing]

  final case class Right[+A](value: A) extends Either[Nothing, A]

  /*
    EXERCISE 8: Implement sequence and traverse for Either.
   */
  def traverseEither[A, B, E](a: List[A])(f: A => Either[E, B]): Either[E, List[B]] = {
    a.foldRight[Either[E, List[B]]](Right(Nil)) { (x, y) => f(x).map2(y)(_ :: _) }
  }

  def sequenceEither[E, A](a: List[Either[E, A]]): Either[E, List[A]] = {
    traverseEither(a)(x => x)
  }
}
