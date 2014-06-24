package com.zuehlke.mlz.kata.pacman

import akka.actor.Actor
import akka.actor.Props
import com.zuehlke.mlz.kata.pacman.model.Position
import akka.actor.ActorLogging

object StaticField {
  def props =  Props[StaticField]
  case object Ok
}

class StaticField extends Actor with ActorLogging{
  import StaticField._
  var size: Int = 0
  
  def receive: Receive = {
    case Pacman.UpdatePosition(pos: Position, dir:(Int, Int)) => {
      val newPosition  = Position(pos.x+ dir._2,  pos.y + dir._2)
      sender ! newPosition
    }
     case field: PacmanManager.SetupField => {
      log.info(s"received request to setup new Field with size $size")
      size = field.size
      sender !Ok
  }
  }
}