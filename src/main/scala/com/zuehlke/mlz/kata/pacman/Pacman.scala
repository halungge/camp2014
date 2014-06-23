package com.zuehlke.mlz.kata.pacman

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorLogging

object Pacman{
  def props : Props = Props[Pacman]
  
  case class Draw(val who:String, val where:(Int, Int))
  case class UpdateDirection(val direction:(Int,Int))
}

class Pacman extends Actor with ActorLogging {
  import Pacman._
 
  var position = (0, 0)
  var direction = (0, 1)
  
  def receive: akka.actor.Actor.Receive = {
    case PacmanManager.Tick => {
     position = (position._1 + direction._1,  position._2 + direction._2)
     sender ! Draw("Pacman", position)
    }
    case Pacman.UpdateDirection(newDirection) => {
      direction = newDirection
    }
    case _=>
  }
}