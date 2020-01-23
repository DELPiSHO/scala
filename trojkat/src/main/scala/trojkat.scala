import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

    case class Wyslij(a: Double,b: Double,c: Double,serwer: ActorRef)
    case class Sprawdz(a: Double,b: Double,c: Double)

class Klient extends Actor {
    def receive: Receive = {
        case Wyslij(a, b, c, serwer) => {
        serwer ! Sprawdz(a,b,c)
        }
        case true =>
            println ("true")
        case false => 
             println("false")
    }
}

class Serwer extends Actor {
    def receive: Receive = {
        //def 
        case Sprawdz(a, b, c) => {
            if ((a+b>c) && (a+c>b) && (b+c>a)) 
            sender() ! true  
            else sender() ! false   
        }
    }
}

object Main extends App {
val system = ActorSystem("sys")
val klient = system.actorOf(Props[Klient],"klient")
val serwer = system.actorOf(Props[Serwer],"serwer")
klient ! Wyslij(2.1,3.1,4.2,serwer)
}
