import controllers.UserStore
import models.User
import utils.ValidationUtility
import io.github.oshai.kotlinlogging.KotlinLogging
import utils.Conversion


// Initialize UserStore
val userStore = UserStore()

private val logger = KotlinLogging.logger {}

// Main function
fun main(){
//    println("Welcome to Health tracker")
    runApp()
    logger.info{"Health Tracker App has started"}

}

// Function to add a user
private fun addUser() {
    println("Please enter the following for the user:")
    userStore.create(getUserDetails())
}

// Function to list all users
private fun listUsers(){
    println("The user details are:")
    userStore.findAll()
        .sortedBy { it.name }
        .forEach{println(it)}

}

// Menu function
private fun menu(): Int{
    print(
        """
        |Main Menu:
        |  1. Add User
        |  2. List Users
        |  3. Delete User
        |  4. Search User by ID
        |  5. Update User
        |  6. Search User by Gender
        |  7. User Report
        |  8. Users (imperial)
        |  0. Exit
        |Please enter your option: 
        """.trimMargin()
    )
    return readlnOrNull()?.toIntOrNull() ?: -1
}

// Run the main application
private fun runApp() {
    var input: Int
    do {
        input = menu()
        when(input) {
            1 -> addUser()
            2 -> listUsers()
            3 -> deleteUser()
            4 -> searchById()
            5 -> updateUser()
            6 -> searchByGender()
            7 -> userReport()
            8 -> userImperial()
            0 -> println("Bye...")
            else -> println("Invalid Option")
        }
    } while (input != 0)
}

// Helper function to get a user by ID
private fun getUserById(): User? {
    print("Enter the id of the user: ")
    return userStore.findOne(readlnOrNull()?.toIntOrNull() ?: -1)
}

// Function to delete a user
private fun deleteUser() {
    val user = getUserById()
    if (user != null && userStore.delete(user.id))
        println("User deleted")
    else
        println("No user found")
}


// Function to search a user by ID
private fun searchById() {
    val user = getUserById()
    if (user == null)
        logger.info{"Search - no user found"}
    else
        println(user)
}

// Function to update a user
private fun updateUser() {
    val foundUser = getUserById()
    if (foundUser != null) {
        val updatedUser = getUserDetails()
        updatedUser.id = foundUser.id
        if (userStore.update(updatedUser))
            println("User updated")
        else
            println("User not updated")
    } else {
        println("User not found")
    }
}

// Function to get user details
private fun getUserDetails(): User {
    val user = User()

    print("     Name: ")
    user.name = readln()

    do {
        print("     Email: ")
        user.email = readln()
        if (!ValidationUtility.validateEmail(user.email)) {
            println("Invalid email format. Please try again.")
        }
    } while (!ValidationUtility.validateEmail(user.email))

    do {
        print("     Weight: ")
        user.weight = readln().toDouble()
        if (!ValidationUtility.validateWeight(user.weight)) {
            println("Invalid weight. Please enter a value between 0 and 300 kg.")
        }
    } while (!ValidationUtility.validateWeight(user.weight))

    do {
        print("     Height: ")
        user.height = readln().toFloat()
        if (!ValidationUtility.validateHeight(user.height)) {
            println("Invalid height. Please enter a value between 0.5 and 2.5 meters.")
        }
    } while (!ValidationUtility.validateHeight(user.height))

    do {
        print("     Gender (M/F/O): ")
        user.gender = readln()
        if (!ValidationUtility.validateGender(user.gender)) {
            println("Invalid gender. Please enter 'M', 'F', or 'O'.")
        }
    } while (!ValidationUtility.validateGender(user.gender))

    return user
}

// Function to search users by gender
private fun searchByGender() {
    print("Enter gender to search (M/F/O): ")
    val gender = readln()

    // Validate gender input
    if (!ValidationUtility.validateGender(gender)) {
        println("Invalid gender. Please enter 'M', 'F', or 'O'.")
        return
    }

    // Filter users by gender and sort by name
    val usersByGender = userStore.findAll()
        .filter { it.gender.equals(gender, ignoreCase = true) }
        .sortedBy { it.name }

    // Display the result
    if (usersByGender.isNotEmpty()) {
        println("Users with gender $gender:")
        usersByGender.forEach { println(it) }
    } else {
        println("No users found with gender $gender.")
    }
}


// Function to calculate BMI
private fun calculateBMI(weight: Double, height: Float): Double {
    return if (height > 0) {
        weight / (height * height)
    } else {
        0.0
    }
}

// Function to generate user report
private fun userReport() {
    println("-----------------------------------------------------")
    println("User Report:")
    println("-----------------------------------------------------")

    userStore.findAll().forEach { user ->
        // Lambda to format and print each user's report
        val bmi = calculateBMI(user.weight, user.height)
        println(
            """
            |Name: ${user.name}
            |Email: ${user.email}
            |Gender: ${user.gender}
            |Height: ${"%.2f".format(user.height)} meters
            |Weight: ${"%.2f".format(user.weight)} kg
            |BMI: ${"%.2f".format(bmi)}
            |-----------------------------------------------------
            """.trimMargin()
        )
    }
}

private fun userImperial(){
    println("Users (Imperial):")
    println("-----------------------------------------------------")

    userStore.findAll().forEach { user ->
        // Convert height to inches and weight to pounds
        val heightInInches = Conversion.convertMetresToInches(user.height.toDouble(), 2.0)
        val weightInPounds = Conversion.convertKGtoPounds(user.weight, 2.0)

        // Lambda to format and print each user's details
        println(
            """
            |Name: ${user.name}
            |Email: ${user.email}
            |Gender: ${user.gender}
            |Height: ${"%.2f".format(heightInInches)} inches
            |Weight: ${"%.2f".format(weightInPounds)} pounds
            |-----------------------------------------------------
            """.trimMargin()
        )
    }
}
