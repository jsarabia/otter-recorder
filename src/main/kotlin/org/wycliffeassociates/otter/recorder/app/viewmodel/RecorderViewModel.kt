package org.wycliffeassociates.otter.recorder.app.viewmodel

import javafx.beans.property.SimpleBooleanProperty
import tornadofx.*

class RecorderViewModel : ViewModel() {

    var recordingProperty = SimpleBooleanProperty(false)
    var isRecording by recordingProperty

    var hasWrittenProperty = SimpleBooleanProperty(false)
    var hasWritten by hasWrittenProperty

    var canSaveProperty = (recordingProperty.not()).and(hasWrittenProperty)

    fun toggle() {
        if(isRecording)
            hasWritten = true
        isRecording = !isRecording
    }
}