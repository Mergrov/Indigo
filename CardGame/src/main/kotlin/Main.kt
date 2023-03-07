package indigo

import kotlin.Exception
import kotlin.system.exitProcess

open class InsufficientNumbersOfCardsExeption : Exception("The remaining cards are insufficient to meet the request.")
class Cards(val rank: Ranks, val suit: Suits) {
    override fun toString(): String = "${rank.rank}${suit.suit}"
    private val Deck by lazy { allCards.toMutableList() }

    enum class Ranks(val rank: String, val strength: Int) {
        Ace("A" ,1),
        Two("2", 2),
        Three("3", 3),
        Four("4", 4),
        Five("5", 5),
        Six("6", 6),
        Seven("7", 7),
        Eight("8", 8),
        Nine("9", 9),
        Ten("10", 10),
        Jack("J", 11),
        Queen("Q",12),
        King("K",13)
    }

    enum class Suits(val suit: String, val color: String) {
        Pikes("♠", "Black"),
        Hearts("♥", "Red"),
        Tiles("♦", "Red"),
        Clovers("♣", "Black");
    }
    companion object {
        private val allCards = buildList {
            Suits.values().forEach { suit ->
                Ranks.values().forEach { rank ->
                    add(Cards(rank, suit))
                }
            }
        }
    }
}
fun placeHolder(){
    println("Bug Me")
}

fun start() {
    var gameHasStarted = false
    var currentdeck = cardPrinter()
    var cardsGiven = mutableListOf<String>()
    var playerHand = mutableListOf<String>()
    var AIHand = mutableListOf<String>()
    while (!gameHasStarted) {
        println("Play first?")
        val inuput = readln()
        when (inuput) {
            "Yes" -> { placeHolder(); gameHasStarted = true }
            "No" -> { placeHolder(); gameHasStarted = true }
            "exit" -> exit()
        }
    }
    while (gameHasStarted){
        shuffleAction(currentdeck)
    }
}

fun cardPrinter(): MutableList<String> {
    val listOfRanks = Cards.Ranks.values().joinToString { it.rank }.split(", ").toList()
    var listOfPikes = mutableListOf<String>()
    var listOfHearts = mutableListOf<String>()
    var listOfTiles = mutableListOf<String>()
    var listOFClovers = mutableListOf<String>()
    var listOfCards = mutableListOf<String>()

    for (element in listOfRanks) {
        listOfPikes.add("$element${Cards.Suits.Pikes.suit}")
        listOfHearts.add("$element${Cards.Suits.Hearts.suit}")
        listOfTiles.add("$element${Cards.Suits.Tiles.suit}")
        listOFClovers.add("$element${Cards.Suits.Clovers.suit}")
    }
    listOfCards.addAll(listOfPikes)
    listOfCards.addAll(listOfHearts)
    listOfCards.addAll(listOfTiles)
    listOfCards.addAll(listOFClovers)
    return listOfCards
}

fun resetAction(deck: MutableList<String>) {
    deck.clear()
    deck.addAll(cardPrinter())
    println("Card deck is reset.")
}

fun shuffleAction(deck: MutableList<String>) {
    deck.shuffled()
}

fun exit() {
    println("Bye")
    exitProcess(0)
}

fun getAction(deck: MutableList<String>): MutableList<String> {
    var cardsGiven = mutableListOf<String>()
    println("Number of cards:")
    try {
        val numberOfCardsToGet = readln().toInt()
        if (numberOfCardsToGet < 1 || numberOfCardsToGet > 52) {
            throw Exception()
        }
        if (numberOfCardsToGet > deck.size) {
            throw InsufficientNumbersOfCardsExeption()
        }
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
    start()
}


