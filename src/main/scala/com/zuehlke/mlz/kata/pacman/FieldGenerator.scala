package com.zuehlke.mlz.kata.pacman

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorLogging

object FieldGenerator {
  def props = Props[FieldGenerator]
}

class FieldGenerator extends Actor with ActorLogging {
  def receive: Receive = {
    case PacmanManager.SetupField => {
      log.info("received request to setup new Field")
      sender ! PacmanManager.Field(10)
    }
    case _ =>
  }
}