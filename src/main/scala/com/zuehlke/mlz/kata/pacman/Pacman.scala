package com.zuehlke.mlz.kata.pacman

import com.zuehlke.mlz.kata.pacman.model.Position
import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.pattern.{ask, pipe}
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Future
import akka.dispatch.OnComplete

object Pacman{
  def props(positionController: ActorRef) : Props = Props(new Pacman(positionController))
  
  case class Draw(who:String, where:Position)
  case class UpdateDirection(direction:(Int,Int))
  case class UpdatePosition(currentPosition: Position, direction: (Int, Int))
}

class Pacman(val field: ActorRef) extends Actor with ActorLogging {
  import Pacman._
 
  var position = Position(0, 0)
  var direction = (0, 1)
  
  
  def receive: Receive = {
    case p:Position => position = p
    case PacmanManager.Tick => {
       //timeout used for the ask method
       implicit val timeout = Timeout(2.seconds)
       //execution Context needed for future
    	 implicit val executionContext = context.system.dispatcher
       val newPosition =  (field ? UpdatePosition(position, direction)).mapTo[Position]
       val nextDraw = newPosition.map(x => Draw("Pacman", x))
       pipe(nextDraw) to sender
       newPosition.onComplete{p => self ! p.get}
     }
    case Pacman.UpdateDirection(newDirection) => {
      log.info(s"changing direction to $direction")
      direction = newDirection
    }
    case _=>
  }
}