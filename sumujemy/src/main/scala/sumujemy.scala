import akka.actor.{ActorSystem, Actor, ActorRef, Props,PoisonPill}

  case class Wyslij(seq: Seq[Int],serwer: ActorRef)
  case class Check(seq: Seq[Int])
  case class Wynik(suma: Int)


class Klient extends Actor {
  def receive: Receive = {
  case Wyslij(seq,serwer) => {
    serwer ! Check(seq)
    }
      
    case Wynik(suma) =>
    println(suma)  
  }
}

class Serwer extends Actor {
  def receive: Receive = {
    case Check(seq) =>
    val suma = seq.foldLeft(0)(_+_)
    sender () ! Wynik(suma)
  }
}
object Main extends App {
  val system = ActorSystem("sys")
  val klient = system.actorOf(Props[Klient], "klient")
  val serwer = system.actorOf(Props[Serwer], "serwer")
  klient ! Wyslij(Seq(1,2,3,4,5,1),serwer)
}
