package org.wycliffeassociates.otter.recorder.app.view

import javafx.geometry.Insets
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import tornadofx.*

interface Drawable {
    fun draw(context: GraphicsContext, canvas: Canvas)
}

class WaveformFragment(color: String) : HBox() {

    val drawables = arrayListOf<Drawable>()

    val cvs = canvas {
        hgrow = Priority.ALWAYS
        vgrow = Priority.ALWAYS
    }
    val ctx = cvs.graphicsContext2D

    init {

        background = Background(BackgroundFill(Paint.valueOf(color), CornerRadii.EMPTY, Insets.EMPTY))

        add(cvs)

        cvs.widthProperty().bind(this.widthProperty())
        cvs.heightProperty().bind(this.heightProperty())

        cvs.widthProperty().addListener { _ -> draw() }
        cvs.heightProperty().addListener { _ -> draw() }
    }

    init {
        addDrawable(BaseWaveLine())
        draw()
    }

    fun addDrawable(drawable: Drawable) {
        drawables.add(drawable)
    }

    fun draw() {
        ctx.clearRect(0.0, 0.0, cvs.width, cvs.height)
        for(d in drawables) {
            d.draw(ctx, cvs)
        }
    }
}

class BaseWaveLine : Drawable {
    override fun draw(context: GraphicsContext, canvas: Canvas) {
        context.setFill(Color.CYAN)
        context.setStroke(Color.CYAN)
        context.setLineWidth(1.5)
        context.strokeLine(0.0, canvas.height / 2.0, canvas.width, canvas.height / 2.0)
    }
}