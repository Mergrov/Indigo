package indigo

import kotlin.Exception
import kotlin.system.exitProcess
open class InsufficientNumbersOfCardsExeption: Exception("The remaining cards are insufficient to meet the request.")
class Cards() {
    var rank: Ranks = Ranks.Ace
    var suit: Suits = Suits.Hearts

    enum class Ranks(val rank: String) {
        Ace("A"),
        Two("2"),
        Three("3"),
        Four("4"),
        Five("5"),
        Six("6"),
        Seven("7"),
        Eight("8"),
        Nine("9"),
        Ten("10"),
        Jack("J"),
        Queen("Q"),
        King("K")
    }
    enum class Suits(val suit: String, val color: String) {
        Pikes("♠", "Black"),
        Hearts("♥", "Red"),
        Tiles("♦", "Red"),
        Clovers("♣", "Black");
    }
}
fun cardPrinter() : MutableList<String>{
    val listOfRanks = Cards.Ranks.values().joinToString { it.rank }.split(", ").toList()
    var listOfPikes = mutableListOf<String>()
    var listOfHearts = mutableListOf<String>()
    var listOfTiles = mutableListOf<String>()
    var listOFClovers = mutableListOf<String>()
    var listOfCards = mutableListOf<String>()

    for (element in listOfRanks){
        listOfPikes.add("$element${Cards.Suits.Pikes.suit}")
        listOfHearts.add("$element${Cards.Suits.Hearts.suit}")
        listOfTiles.add("$element${Cards.Suits.Tiles.suit}")
        listOFClovers.add("$element${Cards.Suits.Clovers.suit}")
    }
    listOfCards.addAll(listOfPikes)
    listOfCards.addAll(listOfHearts)
    listOfCards.addAll(listOfTiles)
    listOfCards.addAll(listOFClovers)
    return  listOfCards
}
fun resetAction(deck: MutableList<String>){
    deck.clear()
    deck.addAll(cardPrinter())
    println("Card deck is reset.")
}
fun shuffleAction(deck: MutableList<String>){
    deck.shuffled()
    println("Card deck is shuffled.")
}
fun exit(){
    println("Bye")
    exitProcess(0)
}
fun getAction(deck: MutableList<String>): MutableList<String> {
    var cardsGiven = mutableListOf<String>()
    println("Number of cards:")
    try {
        val numberOfCardsToGet = readln().toInt()
        if (numberOfCardsToGet < 1 || numberOfCardsToGet > 52) { throw Exception() }
        if (numberOfCardsToGet > deck.size) { throw InsufficientNumbersOfCardsExeption() }
        cardsGiven.addAll(deck.slice(0 until numberOfCardsToGet))
        println(deck.slice(0 until numberOfCardsToGet).joinToString(" "))
        deck.removeAll(cardsGiven)
    } catch (e: InsufficientNumbersOfCardsExeption) {
        println(e.message)
    } catch (e: Exception) {
        println("Invalid number of cards.")
    }
    return cardsGiven
}

fun main() {
    var currentdeck = cardPrinter()
    var cardsGiven = mutableListOf<String>()
    while (true) {
        println("Choose an action (reset, shuffle, get, exit):")
        val inuput = readln()
        when (inuput) {
            "exit" -> exit()
            "reset" -> resetAction(currentdeck)
            "shuffle" -> shuffleAction(currentdeck)
            "get" -> {
                cardsGiven.addAll(getAction(currentdeck))
                currentdeck.removeAll(cardsGiven)
            }
            else -> println("Wrong action.")
        }
    }
}


