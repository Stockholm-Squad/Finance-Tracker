package src.update

// Extension function to parse the date string in "day/month/year" format
fun String.parseDate(): Triple<Int, Int, Int> {
    val dateParts = this.split("/")  // Split the date input by "/"
    if (dateParts.size != 3) throw IllegalArgumentException("Invalid date format")

    val day = dateParts[0].toIntOrNull() ?: throw IllegalArgumentException("Invalid day")
    val month = dateParts[1].toIntOrNull() ?: throw IllegalArgumentException("Invalid month")
    val year = dateParts[2].toIntOrNull() ?: throw IllegalArgumentException("Invalid year")

    return Triple(day, month, year)
}
