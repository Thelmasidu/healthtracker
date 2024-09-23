package utils

//
object ValidationUtility {

    // Gender validation: M (Male), F (Female), O (Other)
    fun validateGender(gender: String): Boolean {
        return gender.uppercase() in listOf("M", "F", "O")
    }

    // Email validation using a simple regex pattern
    fun validateEmail(email: String): Boolean {
        return email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
    }

    // Weight validation: example range 0 to 300 kg
    fun validateWeight(weight: Double): Boolean {
        return weight in 0.0..300.0
    }

    // Height validation: example range 0.5 to 2.5 meters
    fun validateHeight(height: Float): Boolean {
        return height in 0.5f..2.5f
    }
}