import akka.actor.{ActorSystem, Actor, ActorRef, Props,PoisonPill}
object Gracz {
case class
Graj(przeciwnik: ActorRef) 
case object Ping 
case object Pong

}

class Nadzorca extends Actor {
  import Gracz._
  def receive: Receive = {
  case Graj(przeciwnik) => {
    przeciwnik ! Pong
    }
  case Ping => {
    println(s"dostałe Ping od ${sender().path}")
    sender() ! Pong
    }
  case Pong => {
    println(s"dostałe Pong od ${sender().path}")
    sender() ! Ping
    }
  }
}
object Main extends App {
  import Gracz._
  val system = ActorSystem("sys")
  val gracz1 = system.actorOf(Props[Nadzorca], "gracz1")
  val gracz2 = system.actorOf(Props[Nadzorca], "gracz2")
  gracz1 ! Graj(gracz2)
}
