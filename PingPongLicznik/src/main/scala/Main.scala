import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

    case object Ping
    case object Pong
    case class Graj(przeciwnik: ActorRef,ile: Int)
    case class Zacznij(ile: Int)

class Nadzorca extends Actor {
 
 def receive: Receive = {
     case Graj(nadzorca,ile) =>
     context.become(granie(ile))
     nadzorca ! Zacznij(ile)
    
    case Zacznij(ile) => 
    context.become(granie(ile))
    self ! Pong
 }
    def granie(ile: Int): Receive = {
        case Ping => 
    if(ile != 0) {
    sender() ! Pong
    println("Pong")
    context.become(granie(ile-1))
    }

    case Pong =>
    if(ile != 0) {
        sender() ! Ping
        println("Ping")
        context.become(granie(ile-1))
        }
    } 
}
object Main extends App {
val system = ActorSystem("sys")
val nadzorca = system.actorOf(Props[Nadzorca],"nadzorca")
val nadzorca2 = system.actorOf(Props[Nadzorca],"nadzorca2")
nadzorca ! Graj(nadzorca2,10)
}
