import controllers.UserStore
import models.User
import utils.ValidationUtility
import io.github.oshai.kotlinlogging.KotlinLogging

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
fun addUser() {
    println("Please enter the following for the user:")
    userStore.create(getUserDetails())
}

// Function to list all users
fun listUsers(){
    println("The user details are:")
    userStore.findAll().forEach{it -> println(it)}

}

// Menu function
fun menu(): Int{
    print(
        """
        |Main Menu:
        |  1. Add User
        |  2. List Users
        |  3. Delete User
        |  4. Search User by ID
        |  5. Update User
        |  0. Exit
        |Please enter your option: 
        """.trimMargin()
    )
    return readlnOrNull()?.toIntOrNull() ?: -1
}

// Run the main application
fun runApp() {
    var input: Int
    do {
        input = menu()
        when(input) {
            1 -> addUser()
            2 -> listUsers()
            3 -> deleteUser()
            4 -> searchById()
            5 -> updateUser()
            0 -> println("Bye...")
            else -> println("Invalid Option")
        }
    } while (input != 0)
}

// Helper function to get a user by ID
fun getUserById(): User? {
    print("Enter the id of the user: ")
    return userStore.findOne(readlnOrNull()?.toIntOrNull() ?: -1)
}

// Function to delete a user
fun deleteUser() {
    val user = getUserById()
    if (user != null && userStore.delete(user.id))
        println("User deleted")
    else
        println("No user found")
}


// Function to search a user by ID
fun searchById() {
    val user = getUserById()
    if (user == null)
        logger.info{"Search - no user found"}
    else
        println(user)
}

// Function to update a user
fun updateUser() {
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
fun getUserDetails(): User {
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
