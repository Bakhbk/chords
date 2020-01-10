package com.chrynan.guitarchords.model

data class Chord(
        val name: String? = null,
        val markers: Set<ChordMarker>
) {

    val notes: Set<ChordMarker.Note> by lazy { markers.filterIsInstance<ChordMarker.Note>().toSet() }

    val bars: Set<ChordMarker.Bar> by lazy { markers.filterIsInstance<ChordMarker.Bar>().toSet() }

    val opens: Set<ChordMarker.Open> by lazy { markers.filterIsInstance<ChordMarker.Open>().toSet() }

    val mutes: Set<ChordMarker.Muted> by lazy { markers.filterIsInstance<ChordMarker.Muted>().toSet() }

    operator fun contains(marker: ChordMarker) = markers.contains(marker)

    fun getMarkersOnString(number: Int): List<ChordMarker> {
        return markers.filter {
            when {
                it is ChordMarker.Note && it.string.number == number -> true
                it is ChordMarker.Bar && (number in it.startString.number..it.endString.number) -> true
                it is ChordMarker.Open && it.stringNumber.number == number -> true
                it is ChordMarker.Muted && it.stringNumber.number == number -> true
                else -> false
            }
        }
    }
}