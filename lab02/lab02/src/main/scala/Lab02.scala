/*
  Rozwiązując poniższe ćwiczenia NIE korzystaj z następujących
  standardowych operacji na listach:
    – length/size
    – sum
    – map
    – filter
    – ::: (oraz „odmian” typu ++)
    – flatten
    – flatMap
    – reverse (oraz „odmian” tzn. reverseMap, reverse_:::)

  Nie używaj też zmiennych (wprowadzanych za pomocą „var”).
 */

object Lab02 extends App {

  def succ(n: Int) = n + 1
  def pred(n: Int) = n - 1

  // Ćwiczenie 1
  // Nie używaj + ani - na Int. Użyj succ/pred zdefiniowanych powyżej.

  @annotation.tailrec
  def add(x: Int, y: Int): Int = {
    if (y > 0) add(succ(x),pred(y))
    else if (y < 0) add(pred(x),succ(y))
      else 
          x
  }
  println(add(-2,5))

  // Ćwiczenie 2
  
  @annotation.tailrec
  def sum(x: List[Int],acc: Int = 0): Int = x match {
    case List() => acc
    case (glowa :: reszta) => sum(reszta,glowa + acc)
  }
  println(sum(List(1,2,3)))

  // Ćwiczenie 3

  @annotation.tailrec
  def length[A](x: List[A],acc: Int = 0): Int = x match {
    case List() => acc
    case (glowa :: reszta) => length(reszta, acc + 1)
  }
  println(length(List(1,2,3,4,5,6,2)))

  // Ćwiczenie 4
//  @annotation.tailrec

  def map[A, B](x: List[A], f: A => B): List[B] = {
    @annotation.tailrec
    def helper(inp: List[A],out: List[B]):List[B] = inp match {
      case List() => out.reverse
      case glowa :: ogon => helper(ogon,f(glowa) +: out)
    }
    helper(x,List())
  }
  println(map(List(1,2,3,4),(n => "*" * n)))

  // Ćwiczenie 5
  
  def filterThis[A](x: List[A], f: A => Boolean):List[A] ={
      @annotation.tailrec
      def helper(inp: List[A],out: List[A]): List[A] = inp match {
          case List() => out.reverse
          case glowa :: ogon if f(glowa) => helper(ogon,glowa +: out)
          case glowa :: ogon => helper(ogon, out)
    }
    helper(x,List()) 
  }
  println(filterThis(List(-2,3,-2,-1,-100,4,5,6,7,0,111),((n: Int) => n>0)))
  
  // Ćwiczenie 6
  // funkcja "cleanup" powinna usuwać wielokrotne, następujące po sobie
  // wystąpienia tego samego elementu na liście
  // np. cleanup(List(1,1,2,1,3,3)) == List(1,2,1,3)
 
  def cleanup(x: List[Int]): List[Int] = {
    @annotation.tailrec
    def helper(inp: List[Int],out: List[Int]): List[Int] = inp match {
      case List() => out.reverse
      case x1 :: ogon if out.isEmpty => helper(ogon, x1 :: out)
      case x1 :: ogon if x1 != out.head => helper(ogon,x1 :: out)
      case _ => helper(inp.tail,out)
    }
    helper(x,List())
  }
  println(cleanup(List(1,1,1,1,2,2,2,2,1,3,3)))

  // Ćwiczenie 7
  // funkcja "chop" powinna wycinać „podlistę” zaczynającą się od elementu
  // o numerze "b" i kończącą na elemencie o numerze "e" - przyjmijmy, że
  // pierwszy element listy ma numer 1. Przykład
  // chop(List('a,2,'b,3,'c,4),2,4) == List(2,'b,3)
 // @annotation.tailrec
  def chop[A](x: List[A], b: Int, e: Int,ind: Int = 1,out: List[A] = List()): List[A] = x match {
    case List() => out.reverse
    case (glowa :: ogon) => if (ind >= b && ind <= e)
        chop(ogon,b,e,ind+1,glowa :: out)
    else
        chop(ogon,b,e,ind+1,out)
  }
  println(chop(List('a,2,'b,3,'c,4),2,4))

  // Ćwiczenie 8
  // funkcja "remEls" powinna usuwać co "k"-ty element listy. Przykład:
  // remEls(List(1,1,2,1,3,3),3) == List(1,1,1,3)
  @annotation.tailrec
  def remEls[A](x: List[A], k: Int,acc: Int = 1,out: List[A] = List()): List[A] = x match {
    case List() => out.reverse
    case glowa :: ogon => 
    if (acc != k)
        remEls(ogon,k,acc+1,glowa :: out)
    else
        remEls(ogon,k,1,out)
  }
  println(remEls(List(1,1,2,1,3,3),3))

  // Ćwiczenie 9
  // funkcja "rot" powinna przesuwać cyklicznie elementy listy o wartość "k".
  // Przykład:
  // rot(List(1,2,3,4,5,6),3) == List(4,5,6,1,2,3)
//  @annotation.tailrec
  def rot[A](x: List[A], k: Int): List[A] = {
      @annotation.tailrec
      def helper(n: Int, inp: List[A]): List[A] = inp match {
        case List() => inp
        case glowa :: ogon if(n > 0) => helper(n-1, ogon ::: List(glowa))
        case _ => inp
      }
      helper(2,x)   
    }
    println(rot(List(1,2,3,4),2))

}
