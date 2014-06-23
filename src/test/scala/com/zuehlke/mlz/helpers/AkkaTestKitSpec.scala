package com.zuehlke.mlz.helpers

import akka.testkit.TestKit
import akka.actor.ActorSystem
import org.scalatest.MustMatchers
import akka.testkit.ImplicitSender
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfterAll

abstract class AkkaTestKitSpec(name: String) extends TestKit(ActorSystem(name))
  with Matchers 
  with ImplicitSender