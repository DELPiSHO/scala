object Lab05 extends App {
  // program głóewny – tutaj przetestuj swoją implementację MyList
  import MyList._
 // val list = MyEmptyList.add(1).add(2)
 // println(list)

 // list.add(4)
 // println(list)
/*
  def f(x :Int*): Unit = {
    println(x)
    println(x.length)
  }
f(1,2,3)
  */

  object MyList {
    def apply[A](lists: A*): MyList[A] = {
      var wynik: MyList[A] = MyEmptyList
      for (list <- lists) {
        wynik = wynik.add(list)
      }
      wynik
    }
  }
  MyList()
  MyList(1,2,3)
  println(MyList)
}