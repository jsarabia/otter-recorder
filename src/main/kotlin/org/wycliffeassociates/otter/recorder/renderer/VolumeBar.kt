package org.wycliffeassociates.otter.recorder.renderer

import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Paint
import org.wycliffeassociates.otter.recorder.app.view.Drawable


const val RANGE = 32767

class VolumeBar() : Drawable {

    var decible = 0

    private var dbNone: Double = 0.0
    private var dbLow: Double = 0.0
    private var dbGood: Double = 0.0
    private var dbHigh: Double = 0.0
    private var dbMax: Double = 0.0

    override fun draw(context: GraphicsContext, canvas: Canvas) {
        calculateDbPixelLocations(canvas)
        drawBar(canvas, context)
    }

    private fun calculateDbPixelLocations(canvas: Canvas) {
        dbNone = getDbLevel(0, canvas.height, canvas.width)
        dbLow = getDbLevel(2067, canvas.height, canvas.width)    // -24 decibel
        dbGood = getDbLevel(4125, canvas.height, canvas.width)   // -18 decibel
        dbHigh = getDbLevel(23197, canvas.height, canvas.width)  // -3 decibel
        dbMax = getDbLevel(32767, canvas.height, canvas.width)
    }

    private fun drawBar(canvas: Canvas, context: GraphicsContext) {
        val currentDb = getDbLevel(decible, canvas.height, canvas.width)
        val currentDbNeg = getDbLevel(decible * -1, canvas.height, canvas.width)
        setColor(currentDb, context)

        context.fillRect(0.0, dbNone, canvas.width, currentDb)
        context.fillRect(0.0, currentDbNeg, canvas.width, dbNone)
    }

    private fun getDbLevel(db: Int, height: Double, width: Double): Double {
        return (db / (RANGE as Double) * height / 2.0 + width / 2.0)
    }

    private fun setColor(currentDb: Double, ctx: GraphicsContext) {
        ctx.fill = when {
            currentDb < dbLow -> Paint.valueOf("#085394")
            currentDb < dbGood -> Paint.valueOf("#45818E")
            currentDb < dbHigh -> Paint.valueOf("#93C47D")
            currentDb < dbMax -> Paint.valueOf("#FFE599")
            else -> Paint.valueOf("#CF2A27")
        }
    }
}