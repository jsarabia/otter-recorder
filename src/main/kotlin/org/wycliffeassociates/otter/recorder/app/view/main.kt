package org.wycliffeassociates.otter.recorder.app.view

import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.VPos
import javafx.scene.layout.*
import javafx.scene.paint.Paint
import javafx.stage.Screen
import tornadofx.*

fun main(args: Array<String>) {
    launch<RecordingApp>()
}

class RecordingApp: App(RecorderView::class)

class RecorderView : Fragment() {
    override val root = vbox {
        prefHeight = Screen.getPrimary().visualBounds.height - 50.0
        prefWidth = Screen.getPrimary().visualBounds.width - 50.0

        add(InfoFragment())
        add(CanvasFragment())
        add(ControlFragment())
    }
}

class CanvasFragment : Fragment() {

    override val root = gridpane {

        //these are needed for the gridpane itself to fill out the entire width/height allocated to it
        hgrow = Priority.ALWAYS
        vgrow = Priority.ALWAYS



        //you seem to just add things to a column and row index without allocating the number of rows or columns first
        //the classname fragment currently lies- you apparently can only add nodes, not uicomponents? fragment is the latter
        this.add(WaveformFragment("#000000"), 0, 0)
        this.add(WaveformFragment("#FF00FF"), 1, 0)

        //these constraints provide the min/pref/max width, hgrow, hpos, and fill width
        val cc = ColumnConstraints()
        val cc2 = ColumnConstraints()

        cc.percentWidth = 90.0
        cc2.percentWidth = 10.0

        //adding will increment an internal counter, so the first constraint is on the first column, second on second
        this.columnConstraints.add(cc)
        this.columnConstraints.add(cc2)

        //this constraint is necessary for the row to fill out the height of this gridpane
        this.rowConstraints.addAll(RowConstraints(0.0, 0.0, Double.MAX_VALUE, Priority.ALWAYS, VPos.CENTER, true))
    }
}