package com.zuehlke.mlz.kata.pacman

import com.zuehlke.mlz.helpers.AkkaTestKitSpec
import akka.actor.Props
import akka.testkit.TestKit
import org.scalatest.Matchers
import akka.testkit.ImplicitSender
import akka.actor.ActorSystem
import org.scalatest.WordSpecLike
import scala.concurrent.duration._
import org.scalatest.BeforeAndAfterAll
import akka.testkit.TestActorRef
import com.zuehlke.mlz.kata.pacman.model.Position
import com.zuehlke.mlz.kata.pacman.model.Position

class PacmanManagerSpec extends TestKit(ActorSystem("PacmanManager-test"))
  with Matchers 
  with WordSpecLike
  with BeforeAndAfterAll 
  with ImplicitSender {
  
  

  "PacmanManager" should {
	      
    "request new Field when receiving StartUp" in {
    	val pacmanManager = TestActorRef(PacmanManager.props(testActor))
        pacmanManager ! PacmanManager.StartUp
        expectMsg(StaticField.Ok)
    }
	  
	 
	  "pass Draw unto the uiManager upon each tick" in {
	    val pacmanManager = TestActorRef(PacmanManager.props(testActor))
	    pacmanManager ! PacmanManager.Tick
	    expectMsg(Pacman.Draw)
	  }
	  
  }
  
  
  override def afterAll() = {
     system.shutdown()
  }

  
}