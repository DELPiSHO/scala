/*
    PRZYPOMNIENIE:
    =============

    Ćwiczenie 6
    funkcja "cleanup" powinna usuwać wielokrotne, następujące po sobie
    wystąpienia tego samego elementu na liście
    np. cleanup(List(1,1,2,1,3,3)) == List(1,2,1,3)

    Ćwiczenie 7
    funkcja "chop" powinna wycinać „podlistę” zaczynającą się od elementu
    o numerze "b" i kończącą na elemencie o numerze "e" - przyjmijmy, że
    pierwszy element listy ma numer 1. Przykład:
    chop(List('a,2,'b,3,'c,4),2,4) == List(2,'b,3)

    Ćwiczenie 8
    funkcja "remEls" powinna usuwać co "k"-ty element listy. Przykład:
    remEls(List(1,1,2,1,3,3),3) == List(1,1,1,3)
*/

object Lab03 extends App {

    // Ćwiczenie 10. Zdefiniuj funkcję flatenList, która „spłaszcza” ewentualne listy
    // zagnieżdżone w liście będącej jej argumentem:
    // flatenList(List(1,List('a',List(2,'b'))),'c') == List(1,'a',2,'b','c')
    // W definicji flatenList wykorzystaj rekurencję ogonową i wzorce.
    // @annotation.tailrec
    def flatenList(x: List[Any]): List[Any] = ???

    // Ćwiczenie 11. Zaimplementuj uogólnienie funkcji cleanup z Ćwiczenia 6.
    // stosując standardową metodę foldRight (Scala API - IterableOps)
    def cleanup2[A](list: List[A]): List[A] = {
        def helper(el: A,list: List[A]):List[A] = {
            list match {
            case List() => el :: list
            case a1 :: ogon if(el == a1) => list
            case a1 :: ogon => el :: list
        }
    }
    list.foldRight[List[A]](List())(helper)
    }
    println(cleanup2(List(1,1,2,1,3,3)))

    // Ćwiczenie 12. Zaimplementuj funkcję chop z Ćwiczenia 7. stosując
    // standardowe operacje drop i take (Scala API - IterableOps)
    def chop2[A](x: List[A], b: Int, e: Int): List[A] = {
        x.drop(b).take(e)
    }
    println(chop2(List(1,2,3,4,5,6),2,3))

    // Ćwiczenie 13. Zaimplementuj funkcję remEls z Ćwiczenia 8. przy pomocy
    // standardowych metod: filter, map i zipWithIndex (Scala API - IterableOps)
    def remEls2[A](x: List[A], k: Int): List[A] = {
        x.zipWithIndex.filter{
            case(a,b) => (b+1) % k != 0
        }.map{
            case(a,b) => a
        }
    }
    println(remEls2(List(1,1,2,1,3,3),3))
}
