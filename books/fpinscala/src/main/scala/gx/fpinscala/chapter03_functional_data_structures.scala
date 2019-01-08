package gx.fpinscala

import scala.annotation.tailrec
import scala.runtime.Nothing$

/**
  * Created by Joe on 2016/8/26.
  */
object chapter03_functional_data_structures {

  /*
    EXERCISE 2: Implement the tail function for "removing" the first element
    of a List.
   */
  def tail[A](xs: List[A]): List[A] = xs match {
    case head :: tail => tail
    case Nil => Nil
  }

  /*
    EXERCISE 3: Generalize tail to the function drop, which removes the first
    n elements from a list
   */
  @tailrec
  def drop[A](xs: List[A], n: Int): List[A] = {
    if (n == 0) xs
    else drop(tail(xs), n - 1)
  }

  /*
    EXERCISE 4: Implement dropWhile, which removes elements from the
    List prefix as long as they match a predicate.
   */
  @tailrec
  def dropWhile[A](xs: List[A])(f: A => Boolean): List[A] = xs match {
    case head :: tail => if (f(head)) dropWhile(tail)(f) else xs
    case Nil => Nil
  }

  /*
    EXERCISE 5: Using the same idea, implement the function setHead for
    replacing the first element of a List with a different value.
   */
  def setHead[A](xs: List[A], elem: A): List[A] = xs match {
    case head :: tail => elem :: tail
    case Nil => Nil
  }

  /*
    EXERCISE 6: Not everything works out so nicely. Implement a function,
    init, which returns a consisting of all but the last element of a .
   */
  def init[A](xs: List[A]): List[A] = xs match {
    case last :: Nil => Nil
    case head :: last => head :: init(last)
    case Nil => throw new UnsupportedOperationException("empty.init")
  }

  /*
    EXERCISE 7: Can implemented product using foldRight immediately
    halt the recursion and return 0.0 if it encounters a 0.0? Why or why not?
   */
  // No, it can not. Because foldRight always traverses all elements in the list.


  /*
    EXERCISE 9: Compute the length of a list using foldRight.
   */
  def length[A](l: List[A]): Int = {
    l.foldRight(0)((x, y) => y + 1)
  }

  /*
    EXERCISE 10: foldRight is not tail-recursive and will StackOverflow
    for large lists. Convince yourself that this is the case, then write another general
    list-recursion function, foldLeft that is tail-recursive, using the techniques we
    discussed in the previous chapter.
   */
  def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z
    case head :: tail => foldLeft(tail, f(z, head))(f)
  }

  /*
    EXERCISE 11: Write sum, product, and a function to compute the length of
    a list using foldLeft.
   */
  def sum(xs: List[Int]): Int = {
    foldLeft(xs, 0)(_ + _)
  }

  def product(xs: List[Int]): Int = {
    foldLeft(xs, 1)(_ * _)
  }

  def length2[A](xs: List[A]): Int = {
    foldLeft(xs, 0)((x, _) => x + 1)
  }

  /*
    EXERCISE 12: Write a function that returns the reverse of a list (so given
    List(1,2,3) it returns List(3,2,1)). See if you can write it using a fold.
   */
  def reverse[A](xs: List[A]): List[A] = {
    foldLeft(xs, List[A]())((x, y) => y :: x)
  }

  /*
    EXERCISE 13 (hard): Can you write foldLeft in terms of foldRight?
    How about the other way around?
   */
  def foldLeft2[A, B](xs: List[A], z: B)(f: (B, A) => B): B = {
    //xs.reverse.foldRight(z)((a, b) => f(b, a))
    xs.foldRight((b: B) => b)((a, g) => c => g(f(c, a)))(z)
  }

  /*
    EXERCISE 14: Implement append in terms of either foldLeft or
    foldRight
   */
  def append[A](l: List[A], r: List[A]): List[A] = {
    l.foldRight(r)((a, b) => a :: b)
  }

  /*
    EXERCISE 15 (hard): Write a function that concatenates a list of lists into a
    single list. Its runtime should be linear in the total length of all lists. Try to use
    functions we have already defined.
   */
  def concat[A](xs: List[List[A]]): List[A] = {
    xs.foldRight(List[A]())(append)
  }

  /*
    EXERCISE 16: Write a function that transforms a list of integers by adding 1
    to each element. (Reminder: this should be a pure function that returns a new
    List!)
   */
  def add1(xs: List[Int]): List[Int] = {
    xs.map(_ + 1)
  }

  /*
    EXERCISE 17: Write a function that turns each value in a List[Double]
    into a String.
   */
  def doubleToString(xs: List[Double]): List[String] = {
    xs.map(_.toString)
  }

  /*
    EXERCISE 18: Write a function map, that generalizes modifying each element
    in a list while maintaining the structure of the list.
   */
  def map[A, B](xs: List[A])(f: A => B): List[B] = xs match {
    case Nil => Nil
    case head :: tail => f(head) :: map(tail)(f)
  }

  /*
    EXERCISE 19: Write a filter function that removes elements from a list
    unless they satisfy a given predicate. Use it to remote all odd numbers from a
    List[Int]
   */
  def filter[A](xs: List[A])(f: A => Boolean): List[A] = {
    foldLeft(xs, List[A]())((b, a) => if (f(a)) b :+ a else b)
  }

  /*
    EXERCISE 20: Write a function flatMap, that works like map except that
    the function given will return a list instead of a single result, and that list should be
    inserted into the final resulting list.
   */
  def flatMap[A, B](xs: List[A])(f: A => List[B]): List[B] = xs match {
    case Nil => Nil
    case head :: tail => f(head) ::: flatMap(tail)(f)
  }

  /*
    EXERCISE 21: Can you use flatMap to filter implement?
   */
  def filter2[A](xs: List[A])(f: A => Boolean): List[A] = {
    flatMap(xs)(x => if (f(x)) List(x) else List())
  }

  /*
    EXERCISE 22: Write a function that accepts two lists and constructs a new list
    by adding corresponding elements. For example, List(1,2,3) and
    List(4,5,6) becomes List(5,7,9)
   */
  def addList(al: List[Int], bl: List[Int]): List[Int] = (al, bl) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (h1 :: t1, h2 :: t2) => (h1 + h2) :: addList(t1, t2)
  }

  /*
    EXERCISE 23: Generalize the function you just wrote so that it's not specific to
    integers or addition.
   */
  def addListGen[A, B](al: List[A], bl: List[A])(f: (A, A) => B): List[B] = (al, bl) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (h1 :: t1, h2 :: t2) => f(h1, h2) :: addListGen(t1, t2)(f)
  }

  /*
    EXERCISE 24 (hard): As an example, implement hasSubsequence for
    checking whether a contains another as a subsequence. For instance, List List
    List(1,2,3,4) would have List(1,2) List(2,3) List(4) , , and as
    subsequences, among others.
   */
  @tailrec
  def startWith[A](al: List[A], bl: List[A]): Boolean = (al, bl) match {
    case (_, Nil) => true
    case (Nil, _) => false
    case (h1 :: t1, h2 :: t2) => if (h1 == h2) startWith(t1, t2) else false
  }

  @tailrec
  def hasSubsequence[A](l: List[A], sub: List[A]): Boolean = (l, sub) match {
    case (_, Nil) => true
    case (Nil, _) => false
    case (h1 :: t1, _) => if (startWith(l, sub)) true else hasSubsequence(t1, sub)


  }


  sealed trait Tree[+A]

  case class Leaf[A](value: A) extends Tree[A]

  case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

  /*
    EXERCISE 25: Write a function size that counts the number of nodes in a
    tree.
  */
  def size[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(left, right) => 1 + size(left) + size(right)
  }

  /*
    EXERCISE 26: Write a function maximum that returns the maximum element
    in a Tree[Int].
   */
  def maximum(t: Tree[Int]): Int = t match {
    case Leaf(a) => a
    case Branch(left, right) => maximum(left) max maximum(right)
  }

  /*
    EXERCISE 27: Write a function depth that returns the maximum path length
    from the root of a tree to any leaf
   */
  def depth[A](t: Tree[A]): Int = t match {
    case Leaf(a) => 0
    case Branch(left, right) => 1 + (depth(left) max depth(right))
  }

  /*
    EXERCISE 28: Write a map function , analogous to the method of the same
    name on , that modifies each element in a tree with a given function.
   */
  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(a) => Leaf(f(a))
    case Branch(left, right) => Branch(map(left)(f), map(right)(f))
  }

  /*
    EXERCISE 29: Generalize size, maximum, depth and map, writing a new
    function fold that abstracts over their similarities. Reimplement them in terms of
    this more general function.
   */
  def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
    case Leaf(a) => f(a)
    case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
  }

  def size2[A](t: Tree[A]): Int = {
    fold(t)(_ => 1)(1 + _ + _)
  }

  def maximum2(t: Tree[Int]): Int = {
    fold(t)(a => a)((b1, b2) => b1 max b2)
  }

  def depth2[A](t: Tree[A]): Int = {
    fold(t)(a => 0)((b1, b2) => 1 + (b1 max b2))
  }

  def map2[A, B](t: Tree[A])(f: A => B): Tree[B] = {
    fold(t)(a => Leaf(f(a)): Tree[B])((b1, b2) => Branch(b1, b2))
  }
}
