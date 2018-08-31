package tryy.kotlin.model

data class Ticket constructor(val no: Int, val group: Int) : Comparable<Ticket> {

    constructor(no: Int) : this(no, 0){
        print("")
    }

    override fun compareTo(ticket: Ticket): Int {
        return if (ticket.group == this.group) {
            this.no - ticket.no
        } else {
            this.group - ticket.group
        }
    }
}