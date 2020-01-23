import akka.actor.{ActorSystem, Actor, ActorRef, Props,PoisonPill}

object Gracz {
case object Graj
case class Wid(przeciwnik: ActorRef)
case object Ping
case object Pong
case object Peng
}

class Nadzorca extends Actor {
  import Gracz._

def receive: Receive = {
    case Wid(gracz2) => {
      context.become(aktors(gracz2))
    }

}

def aktors(gracz: ActorRef): Receive = {
  case Graj => {
    gracz ! Ping
  }
    case Peng => {
      println("Ping")
      gracz ! Ping
    }
    case Ping => {
      println("Pong")
      gracz ! Pong
    }
    case Pong => {
    println("Peng")
    gracz ! Peng
    }
  }
}

object Main extends App {
  import Gracz._

  val system = ActorSystem("sys")
  val gracz1 = system.actorOf(Props[Nadzorca], "gracz1")
  val gracz2 = system.actorOf(Props[Nadzorca], "gracz2")
  val gracz3 = system.actorOf(Props[Nadzorca], "gracz3")


gracz1 ! Wid(gracz2)
gracz2 ! Wid(gracz3)
gracz3 ! Wid(gracz1)
gracz1 ! Graj
}

