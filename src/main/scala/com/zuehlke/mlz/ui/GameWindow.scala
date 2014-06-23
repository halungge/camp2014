package com.zuehlke.mlz.ui

import swing._
import swing.Swing._
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object Demo extends SimpleSwingApplication
{

  def top = new MainFrame {
    title = "Image Panel Demo"
    size = 640 -> 480
    preferredSize = 640 -> 480

    contents = new ImagePanel
    {
      imagePath = "src/main/resources/pacman.png"
    }
  }
}

class ImagePanel extends Panel
{
  private var _imagePath = ""
  private var bufferedImage:BufferedImage = null

  def imagePath = _imagePath

  def imagePath_=(value:String)
  {
    _imagePath = value
    val imageFile = new File(_imagePath)
    println(imageFile.getAbsolutePath + ", " + imageFile.exists)
    bufferedImage = ImageIO.read(imageFile)
  }



  override def paintComponent(g:Graphics2D): Unit =
  {
    if (null != bufferedImage) g.drawImage(bufferedImage, 320, 240, null)
  }
}

object ImagePanel
{
  def apply() = new ImagePanel()
}