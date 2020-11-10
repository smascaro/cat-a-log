package com.smascaro.listdetail.list.model

data class Breed(
    val name: String,
    val id: String,
    val description: String,
    val temperament: String,
    val lifespan: Lifespan,
    val weight: Weight,
    val affectionLevel: AffectionLevel
) {
    data class Lifespan(val min: Int, val max: Int) {
        companion object {
            private val regexValidFormat = "^\\d* - \\d*\$"
            private val regex = Regex(regexValidFormat)
            fun map(input: String): Lifespan {
                if (!regex.matches(input)) {
                    throw IllegalArgumentException("Input does not follow a valid format. Valid format: $regexValidFormat")
                }
                val agesList = input.split("-".toRegex()).map { it.trim().toInt() }
                return Lifespan(agesList[0], agesList[1])
            }
        }

        override fun toString(): String {
            return "$min to $max years"
        }
    }

    data class Weight(val min: Int, val max: Int) {
        companion object {
            private const val regexValidFormat = "^\\d* - \\d*\$"
            private val regex = Regex(regexValidFormat)
            fun map(input: String): Weight {
                if (!regex.matches(input)) {
                    throw IllegalArgumentException("Input does not follow a valid format. Valid format: $regexValidFormat")
                }
                val weightsList = input.split("-".toRegex()).map { it.trim().toInt() }
                return Weight(weightsList[0], weightsList[1])
            }
        }

        override fun toString(): String {
            return "$min to $max kg"
        }
    }

    sealed class AffectionLevel {
        object VeryAffectionate : AffectionLevel()
        object SomewhatAffectionate : AffectionLevel()
        object DependsOnTheMood : AffectionLevel()
        object NotReallyAffectionate : AffectionLevel()
        object NotAffectionateAtAll : AffectionLevel()
        companion object {
            fun map(input: Int): AffectionLevel {
                return when (input) {
                    1 -> NotAffectionateAtAll
                    2 -> NotReallyAffectionate
                    3 -> DependsOnTheMood
                    4 -> SomewhatAffectionate
                    5 -> VeryAffectionate
                    else -> throw kotlin.IllegalArgumentException("Affection level not between 1 and 5")
                }
            }
        }
    }
}